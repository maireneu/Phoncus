#include <iostream>
#include <sstream>
#include <memory>

#define GLEW_STATIC
#include "GL/glew.h"
#include "GLFW/glfw3.h"

const char* APP_TITLE = "Hello, Modern OpenGL";
const int gWindowWidth = 800;
const int gWindowHeight = 600;
//GLFWwindow* gWindow = nullptr;
struct DestroyglfwWin {
	void operator()(GLFWwindow* ptr) {
		glfwDestroyWindow(ptr);
	}
};

auto gWindow = std::unique_ptr<GLFWwindow, DestroyglfwWin>();

const GLchar* vertexShaderSrc =
"#version 330 core\n"
"layout (location = 0) in vec3 pos;"
"layout (location = 1) in vec3 color;"
"out vec3 vert_color;"
"void main(){"
"	vert_color = color;"	
"	gl_Position = vec4(pos.x, pos.y, pos.z, 1.0);"
"}";



const GLchar* fragmentShaderSrc =
"#version 330 core\n"
"in vec3 vert_color;"
"out vec4 frag_color;"
"void main(){"
"	frag_color = vec4(vert_color, 1.0f);"
"}";


bool gFullscreen = false;

void glfw_onKey(GLFWwindow* window, int key, int scancode, int action, int mode);
void showFPS(GLFWwindow* window);
bool initOpenGL();



int main()
{

	if (!initOpenGL()) {
		std::cerr << "GLFW initialization failed" << std::endl;
		return -1;
	}

	GLfloat vert_pos[] = {
		// position	
		 0.0f,  0.5f,  0.0f, // Top
		 0.5f, -0.5f,  0.0f, // Right
		-0.5f, -0.5f,  0.5f, // Left
	};

	GLfloat vert_color[] = {
		// color
		1.0f, 0.0f, 0.0f,
		0.0f, 1.0f, 0.0f, 
		0.0f, 0.0f, 1.0f
	};

	GLuint vbo_p, vbo_c, vao;

	//gen vertexarray
	glGenVertexArrays(1, &vao);
	glBindVertexArray(vao);

	//gen position buffer
	glGenBuffers(1, &vbo_p);
	glBindBuffer(GL_ARRAY_BUFFER, vbo_p);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vert_pos), vert_pos, GL_STATIC_DRAW);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(GLfloat), NULL);
	glEnableVertexAttribArray(0);

	//gen color buffer
	glGenBuffers(1, &vbo_c);
	glBindBuffer(GL_ARRAY_BUFFER, vbo_c);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vert_color), vert_color, GL_STATIC_DRAW);
	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(GLfloat), NULL);
	glEnableVertexAttribArray(1);

	//gen vertexshader
	GLuint vs = glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vs, 1, &vertexShaderSrc, NULL);
	glCompileShader(vs);

	GLint result;
	GLchar infoLog[512];
	glGetShaderiv(vs, GL_COMPILE_STATUS, &result);
	if (!result) {
		glGetShaderInfoLog(vs, sizeof(infoLog), NULL, infoLog);
		std::cout << "Error! Vertax shader failed to compile. " << infoLog << std::endl;
	}

	//gen fragmentshader
	GLuint fs = glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fs, 1, &fragmentShaderSrc, NULL);
	glCompileShader(fs);

	glGetShaderiv(fs, GL_COMPILE_STATUS, &result);
	if (!result) {
		glGetShaderInfoLog(fs, sizeof(infoLog), NULL, infoLog);
		std::cout << "Error! Fragment shader failed to compile. " << infoLog << std::endl;
	}

	//gen shaderprogram
	GLuint sp = glCreateProgram();
	glAttachShader(sp, vs);
	glAttachShader(sp, fs);
	glLinkProgram(sp);

	glGetProgramiv(sp, GL_LINK_STATUS, &result);
	if (!result) {
		glGetProgramInfoLog(sp, sizeof(infoLog), NULL, infoLog);
		std::cout << "Error! Shader Program linker failure. " << infoLog << std::endl;
	}

	glDeleteShader(vs);
	glDeleteShader(fs);

	// Main loop
	while (!glfwWindowShouldClose(gWindow.get())){
		showFPS(gWindow.get());
		glfwPollEvents();
		glClear(GL_COLOR_BUFFER_BIT);

		glUseProgram(sp);
		glBindVertexArray(vao);
		glDrawArrays(GL_TRIANGLES, 0, 3);
		glBindVertexArray(0);

		glfwSwapBuffers(gWindow.get());
	}

	glDeleteProgram(sp);
	glDeleteVertexArrays(1, &vao);
	glDeleteBuffers(1, &vbo_p);
	glDeleteBuffers(1, &vbo_c);

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
	glfwSwapInterval(1);
	glfwSetKeyCallback(gWindow.get(), glfw_onKey);

	glewExperimental = GL_TRUE;
	if (glewInit() != GLEW_OK) {
		std::cerr << "GLFW initialization failed" << std::endl;
		return false;
	}

	glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	return true;
	
}

void glfw_onKey(GLFWwindow* window, int key, int scancode, int action, int mode) {
	if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
		glfwSetWindowShouldClose(window, GL_TRUE);
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

