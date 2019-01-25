package com.neno.designpattern.structural.composite;

public class Client {
public static void main(String[] args) {
	AbstractFile image = new ImageFile("a.jpg");
	AbstractFile video = new VideoFile("b.mp4");
	AbstractFile text = new TextFile("text.txt");
	//image.killVirus();
	FolderFile folderFilea = new FolderFile("a");
	FolderFile folderFileb = new FolderFile("b");
	folderFilea.addFile(image);
	folderFilea.addFile(text);
	folderFilea.addFile(folderFileb);
	folderFileb.addFile(image);
	folderFileb.addFile(video);
	folderFilea.killVirus();
}
}
