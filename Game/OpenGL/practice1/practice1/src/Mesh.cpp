#include "Mesh.h"
#include <iostream>
#include <sstream>
#include <fstream>

std::vector<std::string> split(std::string s, std::string t) {
	std::vector<std::string> res;
	while (1) {
		int pos = s.find(t);
		if (pos == -1) {
			res.push_back(s);
			break;
		}
		res.push_back(s.substr(0, pos));
		s = s.substr(pos + 1, s.size()  - pos - 1);
	}
	return res;
}

Mesh::Mesh() : mLoaded(false){

}

Mesh::~Mesh() {
	glDeleteVertexArrays(1, &mVAO);
	glDeleteBuffers(1, &mVBO);
}

bool Mesh::loadOBJ(const std::string& filename) {

	std::vector<unsigned int> vertexIndices, uvIndices, normalIndices;
	std::vector<glm::vec3> tempVertices;
	std::vector<glm::vec2> tempUVs;
	std::vector<glm::vec3> tempNormals;


	if (filename.find(".obj") != std::string::npos){
		std::ifstream fin(filename, std::ios::in);
		if (!fin){
			std::cerr << "Can't open " << filename << std::endl;
			return false;
		}
		//if (filename == "models/bowling_pin.obj") std::cout << "a" << std::endl;
		std::cout << "Loading OBJ file " << filename << " ..." << std::endl;

		std::string lineBuffer;
		while (std::getline(fin, lineBuffer)){
			std::stringstream ss(lineBuffer);
			std::string cmd;
			ss >> cmd;
			int d = 0;
			if (cmd == "v"){
				glm::vec3 vertex;
				while (d < 3 && ss >> vertex[d]) d++;
				tempVertices.push_back(vertex);
			}
			else if (cmd == "vt"){
				glm::vec2 uv;
				while (d < 2 && ss >> uv[d]) d++;
				tempUVs.push_back(uv);
			}
			else if (cmd == "vn"){
				glm::vec3 normal;
				while (d < 3 && ss >> normal[d]) d++;
				normal = glm::normalize(normal);
				tempNormals.push_back(normal);
			}
			else if (cmd == "f"){
				std::string faceData;
				int vertexIndex, uvIndex, normalIndex; // v/vt/vn

				while (ss >> faceData) {
					std::vector<std::string> data = split(faceData, "/");

					//vertex index
					if (data[0].size() > 0) {
						sscanf_s(data[0].c_str(), "%d", &vertexIndex);
						vertexIndices.push_back(vertexIndex);
					}

					//texture ÁÂÇ¥
					if (data.size() >= 1) {
						if (data[1].size() > 0) {
							sscanf_s(data[1].c_str(), "%d", &uvIndex);
							uvIndices.push_back(uvIndex);
						}
					}

					// normal index
					if (data.size() >= 2) {
						if (data[2].size() > 0) {
							sscanf_s(data[2].c_str(), "%d", &normalIndex);
							normalIndices.push_back(normalIndex);
						}
					}

				}

				//int p1, p2, p3; //mesh index
				//int t1, t2, t3; //texture index
				//int n1, n2, n3;
				//const char* face = lineBuffer.c_str();
				//int match = sscanf_s(face, "f %i/%i/%i %i/%i/%i %i/%i/%i",
				//	&p1, &t1, &n1,
				//	&p2, &t2, &n2,
				//	&p3, &t3, &n3);
				//if (match != 9)
				//	std::cout << "Failed to parse OBJ file using our very simple OBJ loader" << std::endl;

				//// We are ignoring normals (for now)

				//vertexIndices.push_back(p1);
				//vertexIndices.push_back(p2);
				//vertexIndices.push_back(p3);

				//uvIndices.push_back(t1);
				//uvIndices.push_back(t2);
				//uvIndices.push_back(t3);
			}
		}
		// Close the file
		fin.close();


		// For each vertex of each triangle
		for (unsigned int i = 0; i < vertexIndices.size(); i++)
		{

			Vertex meshVertex;
			if (tempVertices.size() > 0) {
				glm::vec3 vertex = tempVertices[vertexIndices[i] - 1];
				meshVertex.position = vertex;
			}
			if (tempNormals.size() > 0) {
				glm::vec3 normal = tempNormals[normalIndices[i] - 1];
				meshVertex.normal = normal;
			}
			if (tempUVs.size() > 0) {
				glm::vec2 uv = tempUVs[uvIndices[i] - 1];
				meshVertex.texCoords = uv;
			}
			

			mVertices.push_back(meshVertex);


			//// Get the attributes using the indices
			//glm::vec3 vertex = tempVertices[vertexIndices[i] - 1];
			//glm::vec2 uv = tempUVs[uvIndices[i] - 1];

			//Vertex meshVertex;
			//meshVertex.position = vertex;
			//meshVertex.texCoords = uv;

			//mVertices.push_back(meshVertex);
		}
		// Create and initialize the buffers
		initBuffers();
		return (mLoaded = true);
	}

	// We shouldn't get here so return failure
	return false;
}
void Mesh::draw() {
	if (!mLoaded) return;
	glBindVertexArray(mVAO);
	glDrawArrays(GL_TRIANGLES, 0, mVertices.size());
	glBindVertexArray(0);
}

void Mesh::initBuffers() {

	//gen vertexarray
	glGenVertexArrays(1, &mVAO);
	glBindVertexArray(mVAO);

	//gen position buffer
	glGenBuffers(1, &mVBO);
	glBindBuffer(GL_ARRAY_BUFFER, mVBO);
	glBufferData(GL_ARRAY_BUFFER, mVertices.size() * sizeof(Vertex), &mVertices[0], GL_STATIC_DRAW);

	//position
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, sizeof(Vertex), (GLvoid*)0);
	glEnableVertexAttribArray(0);

	//normal
	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, sizeof(Vertex), (GLvoid*)(3 * sizeof(GLfloat)));
	glEnableVertexAttribArray(1);

	//texture
	glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, sizeof(Vertex), (GLvoid*)(6 * sizeof(GLfloat)));
	glEnableVertexAttribArray(2);

	glBindVertexArray(0);

}