#include <iostream>
#include <sstream>
#include <memory>
#include <string>

#define GLEW_STATIC
#include "GL/glew.h"
#include "GLFW/glfw3.h"
#include "glm/glm.hpp"
#include "glm/gtc/matrix_transform.hpp"

#include "ShaderProgram.h"
#include "Texture2D.h"
#include "Camera.h"

const char* APP_TITLE = "Hello, Modern OpenGL";
int gWindowWidth = 1024;
int gWindowHeight = 768;
bool gWireframe = false;
//GLFWwindow* gWindow = nullptr;
struct DestroyglfwWin {
	void operator()(GLFWwindow* ptr) {
		glfwDestroyWindow(ptr);
	}
};

auto gWindow = std::unique_ptr<GLFWwindow, DestroyglfwWin>();

bool gFullscreen = false;
const std::string texture1Filename = "textures/airplane.PNG";
const std::string texture2Filename = "textures/grid.jpg";


//OrbitCamera orbitCamera;
//const float MOUSE_SENSITIVITY = 0.25f;
//float gYaw = 0.0f;
//float gPitch = 0.0f;
//float gRadius = 10.0f;

FPSCamera fpsCamera(glm::vec3(0.0f, 0.0f, -3.0f), glm::vec3(-1.0, -1.0, -1.0));
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

	GLfloat vert_pos[] = {
		// 앞
		-1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
		 1.0f, -1.0f,  1.0f, 1.0f, 0.0f,
		 1.0f,  1.0f,  1.0f, 1.0f, 1.0f,
		-1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
		-1.0f, -1.0f,  1.0f, 0.0f, 0.0f,
		 1.0f, -1.0f,  1.0f, 1.0f, 0.0f,

		 // 뒤
		-1.0f,  1.0f, -1.0f, 0.0f, 1.0f,
		 1.0f, -1.0f, -1.0f, 1.0f, 0.0f,
		 1.0f,  1.0f, -1.0f, 1.0f, 1.0f,
		-1.0f,  1.0f, -1.0f, 0.0f, 1.0f,
		-1.0f, -1.0f, -1.0f, 0.0f, 0.0f,
		 1.0f, -1.0f, -1.0f, 1.0f, 0.0f,

		  // 왼
		-1.0f,  1.0f, -1.0f, 0.0f, 1.0f,
		-1.0f, -1.0f,  1.0f, 1.0f, 0.0f,
		-1.0f,  1.0f,  1.0f, 1.0f, 1.0f,
		-1.0f,  1.0f, -1.0f, 0.0f, 1.0f,
		-1.0f, -1.0f, -1.0f, 0.0f, 0.0f,
		-1.0f, -1.0f,  1.0f, 1.0f, 0.0f,

		  // 오른
		 1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
		 1.0f, -1.0f, -1.0f, 1.0f, 0.0f,
		 1.0f,  1.0f, -1.0f, 1.0f, 1.0f,
		 1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
		 1.0f, -1.0f,  1.0f, 0.0f, 0.0f,
		 1.0f, -1.0f, -1.0f, 1.0f, 0.0f,

		   // 위
		-1.0f,  1.0f, -1.0f, 0.0f, 1.0f,
		 1.0f,  1.0f,  1.0f, 1.0f, 0.0f,
		 1.0f,  1.0f, -1.0f, 1.0f, 1.0f,
		-1.0f,  1.0f, -1.0f, 0.0f, 1.0f,
		-1.0f,  1.0f,  1.0f, 0.0f, 0.0f,
		 1.0f,  1.0f,  1.0f, 1.0f, 0.0f,

		   // 아래
		-1.0f, -1.0f,  1.0f, 0.0f, 1.0f,
		 1.0f, -1.0f, -1.0f, 1.0f, 0.0f,
		 1.0f, -1.0f,  1.0f, 1.0f, 1.0f,
		-1.0f, -1.0f,  1.0f, 0.0f, 1.0f,
		-1.0f, -1.0f, -1.0f, 0.0f, 0.0f,
		 1.0f, -1.0f, -1.0f, 1.0f, 0.0f,
	};

	glm::vec3 cubePos = glm::vec3(0.0f, 0.0f, -5.0f);
	glm::vec3 floorPos = glm::vec3(0.0f, -1.0f, 0.0f);

	GLuint vbo, vao;

	//gen vertexarray
	glGenVertexArrays(1, &vao);
	glBindVertexArray(vao);

	//gen position buffer
	glGenBuffers(1, &vbo);
	glBindBuffer(GL_ARRAY_BUFFER, vbo);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vert_pos), vert_pos, GL_STATIC_DRAW);

	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 5 * sizeof(GLfloat), (GLvoid*)(0));
	glEnableVertexAttribArray(0);


	glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, 5 * sizeof(GLfloat), (GLvoid*)(3 * sizeof(GLfloat)));
	glEnableVertexAttribArray(1);

	ShaderProgram shaderProgram;
	shaderProgram.loadShaders("basic.vert", "basic.frag");

	Texture2D texture1;
	texture1.loadTexture(texture1Filename, true);

	Texture2D texture2;
	texture2.loadTexture(texture2Filename, true);

	//float cubeAngle = 0.0f;
	double lastTime = glfwGetTime();

	// Main loop
	while (!glfwWindowShouldClose(gWindow.get())) {
		showFPS(gWindow.get());
		double currentTime = glfwGetTime();
		double deltaTime = currentTime - lastTime;

		glfwPollEvents();
		update(deltaTime);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		texture1.bind(0);

		//glm::mat4 model(1.0), view(1.0), projection(1.0);
		glm::mat4 model, view, projection;

		/*orbitCamera.setLookAt(cubePos);
		orbitCamera.rotate(gYaw, gPitch);
		orbitCamera.setRadius(gRadius);*/

		model = glm::translate(model, cubePos);
		//view = orbitCamera.getViewMatrix();
		view = fpsCamera.getViewMatrix();


		/*projection = glm::perspective(glm::radians(60.0f), (float)gWindowWidth /
			(float)gWindowHeight, 0.1f, 100.0f);*/
		projection = glm::perspective(glm::radians(fpsCamera.getFOV()), 
			(float)gWindowWidth / (float)gWindowHeight, 0.1f, 100.0f);

		shaderProgram.use();

		shaderProgram.setUniform("model", model);
		shaderProgram.setUniform("view", view);
		shaderProgram.setUniform("projection", projection);

		glBindVertexArray(vao);
		glDrawArrays(GL_TRIANGLES, 0, 36);
		

		texture2.bind(0);
		/*glm::vec3 floorPos;
		floorPos.y = -1.0f;*/
		model = glm::translate(model, floorPos) * glm::scale(model, 
			glm::vec3(10.0f, 0.01f, 10.0f));


		shaderProgram.setUniform("model", model);
		glDrawArrays(GL_TRIANGLES, 0, 36);
		glBindVertexArray(0);

		glfwSwapBuffers(gWindow.get());
		lastTime = currentTime;
	}

	glDeleteVertexArrays(1, &vao);
	glDeleteBuffers(1, &vbo);

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

	glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
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
		fpsCamera.move(MOVE_SPEED * (float)elapsedTime * fpsCamera.getUp());
	else if (glfwGetKey(gWindow.get(), GLFW_KEY_X) == GLFW_PRESS)
		fpsCamera.move(MOVE_SPEED * (float)elapsedTime * -fpsCamera.getUp());
}

void showFPS(GLFWwindow* window) {

	static double previousSeconds = 0.0;
	static int frameCount = 0;
	double elapsedSeconds;
	// returns number of seconds since GLFW started, as a double
	double currentSeconds = glfwGetTime();

	elapsedSeconds = currentSeconds - previousSeconds;
	//limit text update 4 times per second
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