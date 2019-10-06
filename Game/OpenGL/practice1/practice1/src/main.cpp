#include <iostream>
#include <sstream>
#include <string>

#define GLEW_STATIC
#include "GL/glew.h"
#include "GLFW/glfw3.h"
#include "glm/glm.hpp"
#include "glm/gtc/matrix_transform.hpp"

#include "ShaderProgram.h"
#include "Texture2D.h"
#include "Camera.h"
#include "Mesh.h"

const char* APP_TITLE = "Hello, Modern OpenGL";
int gWindowWidth = 1280;
int gWindowHeight = 960;
bool gWireframe = false;
bool gFlashlightOn = true;
glm::vec4 gClearColor(0.2f, 0.2f, 0.2f, 1.0f);

struct DestroyglfwWin {
	void operator()(GLFWwindow* ptr) {
		glfwDestroyWindow(ptr);
	}
};

auto gWindow = std::unique_ptr<GLFWwindow, DestroyglfwWin>();

bool gFullscreen = false;
//const std::string texture1Filename = "textures/airplane.PNG";
//const std::string texture2Filename = "textures/grid.jpg";


//OrbitCamera orbitCamera;
//const float MOUSE_SENSITIVITY = 0.25f;
//float gYaw = 0.0f;
//float gPitch = 0.0f;
//float gRadius = 10.0f;

FPSCamera fpsCamera(glm::vec3(0.0f, 3.0f, 10.0f));// , glm::vec3(-1.0, -1.0, -1.0));
const double ZOOM_SENSITIVITY = -3.0;
const float MOVE_SPEED = 10.0;
const float MOUSE_SENSITIVITY = 0.25f;

void glfw_onKey(GLFWwindow* window, int key, int scancode, int action, int mode);
void glfw_OnFrameBufferSize(GLFWwindow* window, int width, int height);
//void glfw_onMouseMove(GLFWwindow* window, double posX, double posY);
void glfw_onMouseScroll(GLFWwindow* window, double deltaX, double deltaY);
void update(double elapsedTime);
void showFPS(GLFWwindow* window);
bool initOpenGL();

int main()
{

	if (!initOpenGL()) {
		std::cerr << "GLFW initialization failed" << std::endl;
		return -1;
	}

	ShaderProgram lightingShader;
	lightingShader.loadShaders("lighting.vert", "lighting.frag");

	// model ��ġ
	glm::vec3 modelPos[] = {
		glm::vec3(-3.5f, 0.0f, 0.0f),	// barrel
		glm::vec3(4.0f, 1.0f, 0.0f),	// woodcrate
		glm::vec3(0.0f, 0.0f, -3.0f),	// robot
		glm::vec3(0.0f, 0.0f, 0.0f),	// floor
		glm::vec3(0.0f, 0.0f, 3.0f),	// pin
		glm::vec3(-2.0f, 0.0f, 2.0f),	// bunny
		glm::vec3(-5.5f, 0.0f, 0.0f),	// lamp post 1
		glm::vec3(0.0f, 0.0f, 0.0f),	// lamp post 2
		glm::vec3(5.5f, 0.0f, 0.0f),	// lamp post 2
	};

	// model scale
	glm::vec3 modelScale[] = {
		glm::vec3(1.0f, 1.0f, 1.0f),	// barrel
		glm::vec3(1.0f, 1.0f, 1.0f),	// woodcrate
		glm::vec3(1.0f, 1.0f, 1.0f),	// robot
		glm::vec3(10.0f, 1.0f, 10.0f),	// floor
		glm::vec3(0.1f, 0.0f, 0.1f),	// pin
		glm::vec3(0.7f, 0.7f, 0.7f),	// bunny
		glm::vec3(1.0f, 1.0f, 1.0f),	// lamp post 1
		glm::vec3(1.0f, 1.0f, 1.0f),	// lamp post 2
		glm::vec3(1.0f, 1.0f, 1.0f),	// lamp post 3
	};

	//light ��ġ
	glm::vec3 pointLightPos[3] = {
		glm::vec3(-5.0f, 3.8f, 0.0f),
		glm::vec3(0.5f,  3.8f, 0.0f),
		glm::vec3(5.0f,  3.8,  0.0f)
	};

	// Mesh, texture �ε�
	const int numModels = 9;
	Mesh mesh[numModels];
	Texture2D texture[numModels];

	mesh[0].loadOBJ("models/barrel.obj");
	mesh[1].loadOBJ("models/woodcrate.obj");
	mesh[2].loadOBJ("models/robot.obj");
	mesh[3].loadOBJ("models/floor.obj");
	mesh[4].loadOBJ("models/bowling_pin.obj");
	mesh[5].loadOBJ("models/bunny.obj");
	mesh[6].loadOBJ("models/lampPost.obj");
	mesh[7].loadOBJ("models/lampPost.obj");
	mesh[8].loadOBJ("models/lampPost.obj");

	texture[0].loadTexture("textures/barrel_diffuse.png", true);
	texture[1].loadTexture("textures/woodcrate_diffuse.jpg", true);
	texture[2].loadTexture("textures/robot_diffuse.jpg", true);
	texture[3].loadTexture("textures/tile_floor.jpg", true);
	texture[4].loadTexture("textures/AMF.tga", true);
	texture[5].loadTexture("textures/bunny_diffuse.jpg", true);
	texture[6].loadTexture("textures/lamp_post_diffuse.png", true);
	texture[7].loadTexture("textures/lamp_post_diffuse.png", true);
	texture[8].loadTexture("textures/lamp_post_diffuse.png", true);

	double lastTime = glfwGetTime();

	// Main loop
	while (!glfwWindowShouldClose(gWindow.get())) {
		showFPS(gWindow.get());
		double currentTime = glfwGetTime();
		double deltaTime = currentTime - lastTime;

		glfwPollEvents();
		update(deltaTime);

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glm::mat4 model(1.0), view(1.0), projection(1.0);
		view = fpsCamera.getViewMatrix();


		/*projection = glm::perspective(glm::radians(60.0f), (float)gWindowWidth /
			(float)gWindowHeight, 0.1f, 100.0f);*/
		projection = glm::perspective(glm::radians(fpsCamera.getFOV()), 
			(float)gWindowWidth / (float)gWindowHeight, 0.1f, 200.0f);

		glm::vec3 viewPos;
		viewPos.x = fpsCamera.getPosition().x;
		viewPos.y = fpsCamera.getPosition().y;
		viewPos.z = fpsCamera.getPosition().z;

		//glm::vec3 lightPos(0.0f, 1.0f, 10.0f);
		//glm::vec3 lightColor(1.0f, 1.0f, 1.0f);

		//angle += (float)deltaTime * 50.0f;
		//lightPos.x = 8.0f * sinf(glm::radians(angle));

		lightingShader.use();
		lightingShader.setUniform("model", glm::mat4(1.0));
		lightingShader.setUniform("view", view);
		lightingShader.setUniform("projection", projection);
		lightingShader.setUniform("viewPos", viewPos);

		lightingShader.setUniform("sunLight.direction", glm::vec3(0.0f, -0.9f, -0.17f));
		lightingShader.setUniform("sunLight.ambient", glm::vec3(0.1f, 0.1f, 0.1f));
		lightingShader.setUniform("sunLight.diffuse", glm::vec3(0.1f, 0.1f, 0.1f));
		lightingShader.setUniform("sunLight.specular", glm::vec3(0.1f, 0.1f, 0.1f));

		lightingShader.setUniform("pointLights[0].ambient", glm::vec3(0.2f, 0.2f, 0.2f));
		lightingShader.setUniform("pointLights[0].diffuse", glm::vec3(0.0f, 1.0f, 0.1f));
		lightingShader.setUniform("pointLights[0].specular", glm::vec3(1.0f, 1.0f, 1.0f));
		lightingShader.setUniform("pointLights[0].position", pointLightPos[0]);
		lightingShader.setUniform("pointLights[0].constant", 1.0f);
		lightingShader.setUniform("pointLights[0].linear", 0.22f);
		lightingShader.setUniform("pointLights[0].exponent", 0.20f);

		lightingShader.setUniform("pointLights[1].ambient", glm::vec3(0.2f, 0.2f, 0.2f));
		lightingShader.setUniform("pointLights[1].diffuse", glm::vec3(1.0f, 0.1f, 0.0f));
		lightingShader.setUniform("pointLights[1].specular", glm::vec3(1.0f, 1.0f, 1.0f));
		lightingShader.setUniform("pointLights[1].position", pointLightPos[1]);
		lightingShader.setUniform("pointLights[1].constant", 1.0f);
		lightingShader.setUniform("pointLights[1].linear", 0.22f);
		lightingShader.setUniform("pointLights[1].exponent", 0.20f);

		lightingShader.setUniform("pointLights[2].ambient", glm::vec3(0.2f, 0.2f, 0.2f));
		lightingShader.setUniform("pointLights[2].diffuse", glm::vec3(0.0f, 0.1f, 1.0f));
		lightingShader.setUniform("pointLights[2].specular", glm::vec3(1.0f, 1.0f, 1.0f));
		lightingShader.setUniform("pointLights[2].position", pointLightPos[2]);
		lightingShader.setUniform("pointLights[2].constant", 1.0f);
		lightingShader.setUniform("pointLights[2].linear", 0.22f);
		lightingShader.setUniform("pointLights[2].exponent", 0.20f);

		glm::vec3 spotlightPos = fpsCamera.getPosition();
		spotlightPos.y -= 0.5f;

		lightingShader.setUniform("spotLight.ambient", glm::vec3(0.1f, 0.1f, 0.1f));
		lightingShader.setUniform("spotLight.diffuse", glm::vec3(0.8f, 0.8f, 0.8f));
		lightingShader.setUniform("spotLight.specular", glm::vec3(1.0f, 1.0f, 1.0f));
		lightingShader.setUniform("spotLight.position", spotlightPos);
		lightingShader.setUniform("spotLight.direction", fpsCamera.getLook());
		lightingShader.setUniform("spotLight.cosInnerCone", glm::cos(glm::radians(15.0f)));
		lightingShader.setUniform("spotLight.cosOuterCone", glm::cos(glm::radians(20.0f)));
		lightingShader.setUniform("spotLight.constant", 1.0f);
		lightingShader.setUniform("spotLight.linear", 0.07f);
		lightingShader.setUniform("spotLight.exponent", 0.017f);
		lightingShader.setUniform("spotLight.on", gFlashlightOn);

		//lightingProgram.use();
		//lightingProgram.setUniform("model", model);
		//lightingProgram.setUniform("view", view);
		//lightingProgram.setUniform("projection", projection);

		for (int i = 0; i < numModels; i++) {
			model = glm::translate(glm::mat4(1.0), modelPos[i]) * 
				glm::scale(glm::mat4(1.0), modelScale[i]);
			lightingShader.setUniform("model", model);

			lightingShader.setUniform("material.ambient", glm::vec3(0.1f, 0.1f, 0.1f));
			lightingShader.setUniformSampler("material.diffuseMap", 0);
			lightingShader.setUniform("material.specular", glm::vec3(0.8f, 0.8f, 0.8f));
			lightingShader.setUniform("material.shininess", 32.0f);

			texture[i].bind(0);
			mesh[i].draw();
			texture[i].unbind(0);
		}

		//render the light
		//model = glm::translate(glm::mat4(), lightPos);
		//lightShader.use();
		//lightShader.setUniform("lightColor", lightColor);
		//lightShader.setUniform("model", model);
		//lightShader.setUniform("view", view);
		//lightShader.setUniform("projection", projection);
		//lightMesh.draw();

		////glBindVertexArray(vao);
		////glDrawArrays(GL_TRIANGLES, 0, 36);
		

		////texture2.bind(0);
		/*glm::vec3 floorPos;
		floorPos.y = -1.0f;*/
		////model = glm::translate(model, floorPos) * glm::scale(model, 
		////	glm::vec3(10.0f, 0.01f, 10.0f));


		//shaderProgram.setUniform("model", model);
		////glDrawArrays(GL_TRIANGLES, 0, 36);
		////glBindVertexArray(0);

		glfwSwapBuffers(gWindow.get());
		lastTime = currentTime;
	}

	////glDeleteVertexArrays(1, &vao);
	////glDeleteBuffers(1, &vbo);

	glfwTerminate();

	return 0;
}

bool initOpenGL() {

	if (!glfwInit()) {
		std::cerr << "GLFW initialization failed" << std::endl;
		return false;
	}
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

	//auto gWindow = std::unique_ptr<GLFWwindow, DestroyglfwWin>();
	//auto pWindow = std::make_unique<GLFWwindow>(glfwCreateWindow(gWindowWidth, gWindowHeight, APP_TITLE, NULL, NULL));

	if (gFullscreen) {
		GLFWmonitor* pMonitor = glfwGetPrimaryMonitor();
		const GLFWvidmode* pVmode = glfwGetVideoMode(pMonitor);
		if (pVmode != NULL) {
			gWindow.reset(glfwCreateWindow(pVmode->width, pVmode->height, APP_TITLE, pMonitor, NULL));
		}
	}
	else {
		gWindow.reset(glfwCreateWindow(gWindowWidth, gWindowHeight, APP_TITLE, NULL, NULL));
	}

	if (gWindow == nullptr) {
		std::cerr << "Failed to create GLFW window" << std::endl;
		return false;
	}

	glfwMakeContextCurrent(gWindow.get());

	//glfwSwapInterval(1);
	glfwSetKeyCallback(gWindow.get(), glfw_onKey);
	//glfwSetCursorPosCallback(gWindow.get(), glfw_onMouseMove);
	glfwSetFramebufferSizeCallback(gWindow.get(), glfw_OnFrameBufferSize);
	glfwSetScrollCallback(gWindow.get(), glfw_onMouseScroll);

	glfwSetInputMode(gWindow.get(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
	glfwSetCursorPos(gWindow.get(), gWindowWidth / 2.0, gWindowHeight / 2.0);

	glewExperimental = GL_TRUE;
	if (glewInit() != GLEW_OK) {
		std::cerr << "GLFW initialization failed" << std::endl;
		return false;
	}

	//glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	glClearColor(gClearColor.r, gClearColor.g, gClearColor.b, gClearColor.a);
	glViewport(0, 0, gWindowWidth, gWindowHeight);
	glEnable(GL_DEPTH_TEST);
	return true;

}

void glfw_onKey(GLFWwindow* window, int key, int scancode, int action, int mode) {
	if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
		glfwSetWindowShouldClose(window, GL_TRUE);

	if (key == GLFW_KEY_Q && action == GLFW_PRESS) {
		gWireframe = !gWireframe;
		if (gWireframe)
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		else
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}
	if (key == GLFW_KEY_E && action == GLFW_PRESS)
	{
		// toggle the flashlight
		gFlashlightOn = !gFlashlightOn;
	}

}

void glfw_OnFrameBufferSize(GLFWwindow* window, int width, int height) {
	gWindowWidth = width;
	gWindowHeight = height;
	glViewport(0, 0, gWindowWidth, gWindowHeight);
}

//void glfw_onMouseMove(GLFWwindow* window, double posX, double posY) {
//	static glm::vec2 lastMousePos = glm::vec2(0, 0);
//
//	if (glfwGetMouseButton(gWindow.get(), GLFW_MOUSE_BUTTON_LEFT) == 1) {
//		gYaw -= ((float)posX - lastMousePos.x) * MOUSE_SENSITIVITY;
//		gPitch += ((float)posY - lastMousePos.y) * MOUSE_SENSITIVITY;
//	}
//
//	if (glfwGetMouseButton(gWindow.get(), GLFW_MOUSE_BUTTON_RIGHT) == 1) {
//		float dx = 0.01f * ((float)posX - lastMousePos.x);
//		float dy = 0.01f * ((float)posY - lastMousePos.y);
//		gRadius += dx - dy;
//	}
//
//	lastMousePos.x = (float)posX;
//	lastMousePos.y = (float)posY;
//
//}

void glfw_onMouseScroll(GLFWwindow* window, double deltaX, double deltaY)
{
	double fov = fpsCamera.getFOV() + deltaY * ZOOM_SENSITIVITY;

	fov = glm::clamp(fov, 1.0, 120.0);

	fpsCamera.setFOV((float)fov);
}

void update(double elapsedTime)
{
	double mouseX, mouseY;

	glfwGetCursorPos(gWindow.get(), &mouseX, &mouseY);
	fpsCamera.rotate((float)(gWindowWidth / 2.0 - mouseX) * MOUSE_SENSITIVITY, (float)(gWindowHeight / 2.0 - mouseY) * MOUSE_SENSITIVITY);
	glfwSetCursorPos(gWindow.get(), gWindowWidth / 2.0, gWindowHeight / 2.0);

	if (glfwGetKey(gWindow.get(), GLFW_KEY_W) == GLFW_PRESS)
		fpsCamera.move(MOVE_SPEED * (float)elapsedTime * fpsCamera.getLook());
	else if (glfwGetKey(gWindow.get(), GLFW_KEY_S) == GLFW_PRESS)
		fpsCamera.move(MOVE_SPEED * (float)elapsedTime * -fpsCamera.getLook());

	if (glfwGetKey(gWindow.get(), GLFW_KEY_A) == GLFW_PRESS)
		fpsCamera.move(MOVE_SPEED * (float)elapsedTime * -fpsCamera.getRight());
	else if (glfwGetKey(gWindow.get(), GLFW_KEY_D) == GLFW_PRESS)
		fpsCamera.move(MOVE_SPEED * (float)elapsedTime * fpsCamera.getRight());

	if (glfwGetKey(gWindow.get(), GLFW_KEY_Z) == GLFW_PRESS)
		//fpsCamera.move(MOVE_SPEED * (float)elapsedTime * fpsCamera.getUp());
		fpsCamera.move(MOVE_SPEED * (float)elapsedTime * glm::vec3(0.0f, 1.0f, 0.0f));
	else if (glfwGetKey(gWindow.get(), GLFW_KEY_X) == GLFW_PRESS)
		//fpsCamera.move(MOVE_SPEED * (float)elapsedTime * -fpsCamera.getUp());
		fpsCamera.move(MOVE_SPEED * (float)elapsedTime * -glm::vec3(0.0f, 1.0f, 0.0f));
}

void showFPS(GLFWwindow* window) {

	static double previousSeconds = 0.0;
	static int frameCount = 0;
	double elapsedSeconds;
	double currentSeconds = glfwGetTime();

	elapsedSeconds = currentSeconds - previousSeconds;

	if (elapsedSeconds > 0.25) {
		previousSeconds = currentSeconds;
		double fps = (double)frameCount / elapsedSeconds;
		double msPerFrame = 1000.0 / fps;

		std::ostringstream outs;
		outs.precision(3);
		outs << std::fixed
			<< APP_TITLE << "   "
			<< "FPS: " << fps << "   "
			<< "Frame Time: " << msPerFrame << "  (ms)";
		glfwSetWindowTitle(window, outs.str().c_str());

		frameCount = 0;
	}

	frameCount++;
}