#include "Texture2D.h"
#include "stb_image/stb_image.h"
#include <iostream>

Texture2D::Texture2D() : mTexture(0){

}
Texture2D::~Texture2D() {

}

bool Texture2D::loadTexture(const std::string& filename, bool generateMipMaps){
	int width, height, components;

	unsigned char* imageData = stbi_load(filename.c_str(), &width, &height, &components, STBI_rgb_alpha);
	if (imageData == NULL) {
		std::cerr << "Error loading texture '" << filename << "'" << std::endl;
		return false;
	}

	glGenTextures(1, &mTexture);
	glBindTexture(GL_TEXTURE_2D, mTexture);

	glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S, GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

	glTexImage2D(GL_TEXTURE_2D, );

	return true;
}
void Texture2D::bind(GLuint texUnit = 0) {

}

