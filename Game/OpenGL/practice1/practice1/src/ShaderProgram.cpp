#include "ShaderProgram.h"
#include <fstream>
#include <iostream>
#include <sstream>

ShaderProgram::ShaderProgram() : mHandle(0) {

}

ShaderProgram::~ShaderProgram() {
	glDeleteProgram(mHandle);
}


bool ShaderProgram::loadShaders(const char* vsFilename, const char* fsFilename) {

	std::string vsString = fileToString(vsFilename);
	std::string fsString = fileToString(fsFilename);
	const GLchar* vsSourcePtr = vsString.c_str();
	const GLchar* fsSourcePtr = fsString.c_str();
	//auto vsSourcePtr = std::unique_ptr<const GLchar>(vsString.c_str());
	//auto fsSourcePtr = std::unique_ptr<const GLchar>(fsString.c_str());


	//gen shader
	GLuint vs = glCreateShader(GL_VERTEX_SHADER);
	GLuint fs = glCreateShader(GL_FRAGMENT_SHADER);

	glShaderSource(vs, 1, &vsSourcePtr, NULL);
	glShaderSource(fs, 1, &fsSourcePtr, NULL);

	glCompileShader(vs);
	checkCompileErrors(vs, VERTEX);

	glCompileShader(fs);
	checkCompileErrors(fs, VERTEX);

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

	return true;
}
void ShaderProgram::use() {
	if (mHandle > 0)
		glUseProgram(mHandle);
}

std::string ShaderProgram::fileToString(const std::string& filename) {
	std::stringstream ss;
	std::ifstream file;

	try {
		file.open(filename, std::ios::in);
		if (!file.fail()) {
			ss << file.rdbuf();
		}

		file.close();
	}
	catch(std::exception e){
		std::cerr << "Error reading shader filename!" << std::endl;
	}

	return ss.str();
}
void ShaderProgram::checkCompileErrors(GLuint shader, ShaderType type) {

};