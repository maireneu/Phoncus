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

	//gen shader
	GLuint vs = glCreateShader(GL_VERTEX_SHADER);
	GLuint fs = glCreateShader(GL_FRAGMENT_SHADER);

	glShaderSource(vs, 1, &vsSourcePtr, NULL);
	glShaderSource(fs, 1, &fsSourcePtr, NULL);

	glCompileShader(vs);
	checkCompileErrors(vs, VERTEX);

	glCompileShader(fs);
	checkCompileErrors(fs, FRAGMENT);
	//gen shaderprogram
	mHandle = glCreateProgram();
	if (mHandle == 0){
		std::cerr << "Unable to create shader program!" << std::endl;
		return false;
	}
	glAttachShader(mHandle, vs);
	glAttachShader(mHandle, fs);

	glLinkProgram(mHandle);
	checkCompileErrors(mHandle, PROGRAM);

	glDeleteShader(vs);
	glDeleteShader(fs);

	mUniformLocation.clear();

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
	int status = 0;
	if (type == PROGRAM) {
		glGetProgramiv(mHandle, GL_LINK_STATUS, &status);
		if (status == GL_FALSE) {
			GLint length = 0;
			glGetProgramiv(mHandle,GL_INFO_LOG_LENGTH, &length);
			std::string errorLog(length, ' ');
			glGetProgramInfoLog(mHandle, length, &length, &errorLog[0]);
			std::cerr << "Error! Program failed to link." << errorLog << std::endl;
		}
	}
	else { //VERTEX or FRAGMENT
		glGetShaderiv(shader, GL_COMPILE_STATUS, &status);
		if (status == GL_FALSE) {
			GLint length = 0;
			glGetShaderiv(mHandle, GL_INFO_LOG_LENGTH, &length);
			std::string errorLog(length, ' ');
			glGetShaderInfoLog(mHandle, length, &length, &errorLog[0]);
			std::cerr << "Error! Shader failed to compile." << errorLog << std::endl;
		}
	}
};

GLint ShaderProgram::getUniformLocation(const GLchar* name) {
	std::map<std::string, GLint>:: iterator it = mUniformLocation.find(name);

	if (it == mUniformLocation.end()) {
		mUniformLocation[name] = glGetUniformLocation(mHandle, name);
	}

	return mUniformLocation[name];
}

GLuint ShaderProgram::GetProgram()const {
	return mHandle;
}

void ShaderProgram::setUniform(const GLchar* name, const glm::vec2& v) {
	GLint loc = getUniformLocation(name);
	glUniform2f(loc, v.x,v.y);
}
void ShaderProgram::setUniform(const GLchar* name, const glm::vec3& v) {
	GLint loc = getUniformLocation(name);
	glUniform3f(loc, v.x, v.y, v.z);
}
void ShaderProgram::setUniform(const GLchar* name, const glm::vec4& v) {
	GLint loc = getUniformLocation(name);
	glUniform4f(loc, v.x, v.y, v.z, v.w);
}