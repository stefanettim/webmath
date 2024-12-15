package neural.reader;

import neural.ImageUtils;

public class ReadResult {

	private int result;

	private String originalImage;
	private String convertedImage;
	private String subImage;
	private String squareImage;
	private String smallImage;
	private String massCenterImage;
	private String mnistImage;

	private float[] floatImage;

	private float[] activations;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getOriginalImage() {
		return originalImage;
	}

	public void setOriginalImage(String originalImage) {
		this.originalImage = originalImage;
	}

	public String getSubImage() {
		return subImage;
	}

	public void setSubImage(String subImage) {
		this.subImage = subImage;
	}

	public String getSquareImage() {
		return squareImage;
	}

	public void setSquareImage(String squareImage) {
		this.squareImage = squareImage;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getMnistImage() {
		return mnistImage;
	}

	public void setMnistImage(String mnistImage) {
		this.mnistImage = mnistImage;
	}

	public float[] getActivations() {
		return activations;
	}

	public void setActivations(float[] activations) {
		this.activations = activations;
	}

	public float[] getFloatImage() {
		return floatImage;
	}

	public void setFloatImage(float[] floatImage) {
		this.floatImage = floatImage;
	}

	public String getMassCenterImage() {
		return massCenterImage;
	}

	public void setMassCenterImage(String massCenterImage) {
		this.massCenterImage = massCenterImage;
	}

	public String getConvertedImage() {
		return convertedImage;
	}

	public void setConvertedImage(String convertedImage) {
		this.convertedImage = convertedImage;
	}

	public void load(ImageChain ic) {
		setOriginalImage(ImageUtils.encodeToString(ic.originalBufferedImage));
		setConvertedImage(ImageUtils.encodeToString(ic.convertedBufferedImage));
		setSubImage(ImageUtils.encodeToString(ic.subBufferedImage));
		setSquareImage(ImageUtils.encodeToString(ic.squareBufferedImage));
		setSmallImage(ImageUtils.encodeToString(ic.smallBufferedImage));
		setMassCenterImage(ImageUtils.encodeToString(ic.centerMassBufferedImage));
		setMnistImage(ImageUtils.encodeToString(ic.mnistBufferedImage));
		setFloatImage(ic.mnist);
	}

}
