#ifndef TEXTURE2D_H
#define TEXTURE2D_H

#define GLEW_STATIC
#include "GL/glew.h"
#include <string>

class Texture2D {

public:
	Texture2D();
	virtual ~Texture2D();

	bool loadTexture(const std::string& filename, bool generateMipMaps = true);
	void bind(GLuint texUnit = 0);

private:
	Texture2D(const Texture2D& rhs) {}
	Texture2D& operator = (const Texture2D& rhs) {}
	GLuint mTexture;

};

#endif // TEXTURE2D_H