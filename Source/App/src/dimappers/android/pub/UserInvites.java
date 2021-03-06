package dimappers.android.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UserInvites extends Activity implements OnClickListener{

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_invites);
		
    	findViewById(R.id.textView7).setVisibility(View.GONE);
    	findViewById(R.id.editText1).setVisibility(View.GONE);
    	findViewById(R.id.make_a_comment).setVisibility(View.GONE);
    	findViewById(R.id.send).setVisibility(View.GONE);
    	
    	Button button_going = (Button)findViewById(R.id.going);
    	button_going.setOnClickListener(this);
    	
    	Button button_decline = (Button) findViewById(R.id.decline);
    	button_decline.setOnClickListener(this);
    	
    	Button button_make_comment = (Button) findViewById(R.id.make_a_comment);
    	button_make_comment.setOnClickListener(this);
    	
    	/*	TODO: Passing values to determine which page loaded this one: going or waiting for response to know the status.
    	  	Attending button made green if going
    	  	Decline button made red if not going
    	  	Neither colour if undecided
    	  	
    	  	Make available from time textbox open time dialog and automatically have current start time put in
    	  
    	 	
    	 */
    	
    	

    	ListView list = (ListView) findViewById(R.id.listView1);
    	 
    	ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("Comment", "yes");
    	map.put("Guest", "Jason Karp");
    	map.put("Available From Time", "8:00 PM");
    	mylist.add(map);
    	map = new HashMap<String, String>();
    	map.put("Comment", "no");
    	map.put("Guest", "Tom Kiley");
    	map.put("Available From Time", "8:15 PM");
    	mylist.add(map);
    	map = new HashMap<String, String>();
    	map.put("Comment", "no");
    	map.put("Guest", "Tom Nicholls");
    	map.put("Available From Time", "9:15 PM");
    	mylist.add(map);
    	map = new HashMap<String, String>();
    	map.put("Comment", "no");
    	map.put("Guest", "Kim Barrett");
    	map.put("Available From Time", "9:00 PM");
    	mylist.add(map);
    	map = new HashMap<String, String>();
    	map.put("Comment", "no");
    	map.put("Guest", "Mark Fearnley");
    	map.put("Available From Time", " ");
    	mylist.add(map);
    
    	SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.row,
    	            new String[] {"Comment", "Guest", "Available From Time"}, new int[] {R.id.envelope, R.id.guest, R.id.time});
    	list.setAdapter(mSchedule);
	}
	
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	  //MenuInflater myMenuInflater = getMenuInflater();
	  //myMenuInflater.inflate(R.menu.menu, menu);
	    // return true;
		 
		 menu.add(R.string.edit);  
		 menu.add(R.string.delete_event);
		 menu.add(R.string.cancel);
		 return super.onCreateOptionsMenu(menu);
	 }

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	  
		Intent i;	 
		 
		switch(item.getItemId()){
	    case(R.id.edit_button):
	    {
			i = new Intent(this, Organise.class);
			startActivity(i);
	    
			break;
	    }
	    case(R.id.delete_Event):
	    {
	    	displayAlert();
	    	break; 
	    }
	    case(R.string.cancel):
	    {
	    break;
	    }
	  } 
	  return true;
	 } 
	
	
	public void onClick(View v)
	{
		Intent i;
		
		switch (v.getId()) {
		case R.id.going : 
		{
			findViewById(R.id.textView7).setVisibility(View.VISIBLE);
	    	findViewById(R.id.editText1).setVisibility(View.VISIBLE);
	    	findViewById(R.id.make_a_comment).setVisibility(View.VISIBLE);
	    	findViewById(R.id.send).setVisibility(View.VISIBLE);
			break;
		}
		case R.id.decline :
		{
	    	findViewById(R.id.make_a_comment).setVisibility(View.VISIBLE);
	    	findViewById(R.id.send).setVisibility(View.VISIBLE);
	    	findViewById(R.id.textView7).setVisibility(View.GONE);
	    	findViewById(R.id.editText1).setVisibility(View.GONE);
			break;
		}
		case R.id.make_a_comment :
		{
			showAddDialog();
			break;
		}
		}
    }
	
	private void showAddDialog() 
	{
		 final Dialog commentDialog = new Dialog(UserInvites.this);
         commentDialog.setContentView(R.layout.making_comment);
         commentDialog.setTitle("Do you want to make a comment?");
         commentDialog.setCancelable(true);
		
        TextView text = (TextView) commentDialog.findViewById(R.id.comment_text_box);

		Button attachButton = (Button) commentDialog.findViewById(R.id.attach); 
		Button cancelButton = (Button) commentDialog.findViewById(R.id.cancel); 

		attachButton.setOnClickListener(new OnClickListener() { 
		// @Override 
		public void onClick(View v) { 

		Toast.makeText(getBaseContext(), "Make a comment", 
		Toast.LENGTH_LONG).show(); 
		} 
		}); 

		cancelButton.setOnClickListener(new OnClickListener() { 
		// @Override 
		public void onClick(View v) { 
		commentDialog.dismiss(); 
		} 
		});
		
		commentDialog.show();
	}
		


	public  void displayAlert()
	{
		new AlertDialog.Builder(this).setMessage("Are you sure you want to delete this event?")  
	       .setTitle("Alert")  
	       .setCancelable(true)  
	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	       public void onClick(DialogInterface dialog, int id) {
	            finish();
	            //TODO: Actually deletes the event!!!
	       }
	       })
	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
	       public void onClick(DialogInterface dialog, int id) {
	            dialog.cancel();
	       }
	       })
	       .show(); 
	       
	}

}