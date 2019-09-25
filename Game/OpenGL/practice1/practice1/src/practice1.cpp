#include <iostream>
#include <sstream>
#include <memory>

#define GLEW_STATIC
#include "GL/glew.h"
#include "GLFW/glfw3.h"

const char* APP_TITLE = "Introduction to Modern OpenGL - Hello Window 1";
const int gWindowWidth = 800;
const int gWindowHeight = 600;
bool gFullscreen = false;

void glfw_onKey(GLFWwindow* window, int key, int scancode, int action, int mode);
void showFPS(GLFWwindow* window);

struct DestroyglfwWin {
	void operator()(GLFWwindow* ptr) {
		glfwDestroyWindow(ptr);
	}
};

int main()
{
	if (!glfwInit()) {
		std::cerr << "GLFW initialization failed" << std::endl;
		return -1;
	}
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
	
	
	auto pWindow = std::unique_ptr<GLFWwindow, DestroyglfwWin>();
	//auto pWindow = std::make_unique<GLFWwindow>(glfwCreateWindow(gWindowWidth, gWindowHeight, APP_TITLE, NULL, NULL));
	
	if (gFullscreen) {
		GLFWmonitor* pMonitor = glfwGetPrimaryMonitor();
		const GLFWvidmode* pVmode = glfwGetVideoMode(pMonitor);
		if (pVmode != NULL) {
			pWindow.reset(glfwCreateWindow(pVmode->width, pVmode->height, APP_TITLE, pMonitor, NULL));
		}
	}
	else {
		pWindow.reset(glfwCreateWindow(gWindowWidth, gWindowHeight, APP_TITLE, NULL, NULL));
	}

	if (pWindow == nullptr) {
		std::cerr << "Failed to create GLFW window" << std::endl;
		return -1;
	}
	
	glfwMakeContextCurrent(pWindow.get());
	glfwSwapInterval(1);
	glfwSetKeyCallback(pWindow.get(), glfw_onKey);

	glewExperimental = GL_TRUE;
	if (glewInit() != GLEW_OK) {
		std::cerr << "GLFW initialization failed" << std::endl;
		return -1;
	}
	bool winkle = true;
	// Main loop
	while (!glfwWindowShouldClose(pWindow.get())){
		showFPS(pWindow.get());
		glfwPollEvents();
		if (winkle) {
			glClearColor(0.23f, 0.38f, 0.47f, 1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
			winkle = false;
		}
		else {
			glClearColor(0.1f, 0.8f, 0.8f, 1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
			winkle = true;
		}

		glfwSwapBuffers(pWindow.get());
	}

	return 0;
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

