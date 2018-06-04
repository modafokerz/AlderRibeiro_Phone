/*
* Exercice FIG HES-SO (Sierre)
* Auteur : Nelson Ribeiro Teixeira
* Date de création : 4 juin 2018
* Date de modification : /
*/
package nav;

public class GalleryPicScreen extends GalleryScreen {
	private GalleryPicture chosenPic;
	private String[] pictureInformations;
	
	private topButton returnButton = new topButton("img/icons/return-icon.png");
	private topButton deleteButton = new topButton("img/icons/delete-pic-icon.png");
	private topLabel picName;
	
	public GalleryPicScreen(GalleryPicture picture) {
		chosenPic = picture;
		pictureInformations = chosenPic.getPictureInformations();
		picName = new topLabel(pictureInformations[0]);
		
		setTopPanel(returnButton,picName,deleteButton);
		
	}

}
