package com.kerrel.imageresizer_lib;

import java.io.File;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.kerrel.imageresizer_lib.operations.ImageCrop;
import com.kerrel.imageresizer_lib.operations.ImageResize;
import com.kerrel.imageresizer_lib.operations.ImageRotate;
import com.kerrel.imageresizer_lib.operations.ImageRotation;
import com.kerrel.imageresizer_lib.operations.ResizeMode;
import com.kerrel.imageresizer_lib.utils.ImageWriter;


public class ImageResizer {

	// Resize
	public static Bitmap resize(File original, int width, int height) {
		return ImageResize.resize(original, width, height, null);
	}
	
	public static Bitmap resize(File original, int width, int height, ResizeMode mode) {
		return ImageResize.resize(original, width, height, mode);
	}
	
	public static Bitmap resize(byte[] byteArray, int width, int height) {
		return ImageResize.resize(byteArray, width, height, null);
	}
	
	public static Bitmap resize(byte[] byteArray, int width, int height, ResizeMode mode) {
		return ImageResize.resize(byteArray, width, height, mode);
	}
	
	public static Bitmap resize(Resources resources, int resId, int width, int height) {
		return ImageResize.resize(resources, resId, width, height, null);
	}
	
	public static Bitmap resize(Resources resources, int resId, int width, int height, ResizeMode mode) {
		return ImageResize.resize(resources, resId, width, height, mode);
	}
	
	// Crop
	public static Bitmap crop(File original, int width, int height) {
		return ImageCrop.crop(original, -1, -1, width, height);
	}
	
	public static Bitmap crop(File original, int x, int y, int width, int height) {
		return ImageCrop.crop(original, x, y, width, height);
	}
	
	public static Bitmap crop(byte[] byteArray, int width, int height) {
		return ImageCrop.crop(byteArray, -1, -1, width, height);
	}
	
	public static Bitmap crop(byte[] byteArray, int x, int y, int width, int height) {
		return ImageCrop.crop(byteArray, x, y, width, height);
	}
	
	public static Bitmap crop(Resources resources, int resId, int width, int height) {
		return ImageCrop.crop(resources, resId, -1, -1, width, height);
	}
	
	public static Bitmap crop(Resources resources, int resId, int x, int y, int width, int height) {
		return ImageCrop.crop(resources, resId, x, y, width, height);
	}
	
	// Rotate
	public static Bitmap rotate(File original, ImageRotation rotation) {
		return ImageRotate.rotate(original, rotation);
	}
	
	public static Bitmap rotate(byte[] byteArray, ImageRotation rotation) {
		return ImageRotate.rotate(byteArray, rotation);
	}
	
	public static Bitmap rotate(Resources resources, int resId, ImageRotation rotation) {
		return ImageRotate.rotate(resources, resId, rotation);
	}
	
	public static void saveToFile(Bitmap image, File file) {
		ImageWriter.writeToFile(image, file);
	}
	
}
