package com.neno.designpattern.structural.composite;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractFile {
	protected String fileName;

	public AbstractFile(String fileName) {
		this.fileName = fileName;
	}

	public abstract void killVirus();
}

class ImageFile extends AbstractFile {

	public ImageFile(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void killVirus() {
		System.out.println("--查杀图像-- " + fileName + " --文件--");

	}

}

class VideoFile extends AbstractFile {

	public VideoFile(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void killVirus() {
		System.out.println("--查杀视频-- " + fileName + " --文件--");

	}

}

class TextFile extends AbstractFile {

	public TextFile(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void killVirus() {
		System.out.println("--查杀文本-- " + fileName + " --文件--");

	}

}

class FolderFile extends AbstractFile {
	private List<AbstractFile> lists = new ArrayList<>();

	public FolderFile(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	public void addFile(AbstractFile aFile) {
		lists.add(aFile);
	}

	public void removeFile(AbstractFile aFile) {
		lists.remove(aFile);
	}

	public AbstractFile getFile(int index) {
		return lists.get(index);
	}

	@Override
	public void killVirus() {
		System.out.println("--查杀文件夹-- " + fileName + " --文件--");
		for (AbstractFile abstractFile : lists) {
			abstractFile.killVirus();
		}

	}

}