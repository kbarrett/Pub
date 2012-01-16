package dimappers.android.pub;

import android.provider.MediaStore.Images;
import dimappers.android.PubData.Guest;
import dimappers.android.PubData.PubTripState;

public class AppGuest extends Guest
{
	//Properties
	
	//Constructors
	public AppGuest(String facebookUserName)
	{
		super(facebookUserName);
	}
	
	//Public methods
	
	//Get facebook profile picture
	Images GetFacebookImage()
	{
		return null;		
	}
	
	//Get full facebook name, ie Thomas Kiley instead of thomas.kiley
	String GetRealFacebookName()
	{
		return super.GetFacebookUserName();
	}

}