package kth.vhung.traveldiary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


public class MainActivity extends FragmentActivity implements 
	MyListFragment.OnHeadlineSelectedListener{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_list);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            MyListFragment firstFragment = new MyListFragment();
            
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
            
            
        }
    }

	@Override
	public void onSelected(int position) {
//	        // The user selected the headline of an article from the HeadlinesFragment
//
//	        // Capture the article fragment from the activity layout
//	        DetailFragment articleFrag = (Fragment)
//	                getSupportFragmentManager().findFragmentById(R.id.article_fragment);
//
//	        if (articleFrag != null) {
//	            // If article frag is available, we're in two-pane layout...
//
//	            // Call a method in the DetailFragment to update its content
//	            articleFrag.updateArticleView(position);
//
//	        } else {
//	            // If the frag is not available, we're in the one-pane layout and must swap frags...
//
//	            // Create fragment and give it an argument for the selected article
//	            DetailFragment newFragment = new DetailFragment();
//	            Bundle args = new Bundle();
//	            args.putInt(DetailFragment.ARG_POSITION, position);
//	            newFragment.setArguments(args);
//	            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//	            // Replace whatever is in the fragment_container view with this fragment,
//	            // and add the transaction to the back stack so the user can navigate back
//	            transaction.replace(R.id.fragment_container, newFragment);
//	            transaction.addToBackStack(null);
//
//	            // Commit the transaction
//	            transaction.commit();
//	        }		
	}
}

/*import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// When user clicks button, calls AsyncTask.
    // Before attempting to fetch the URL, makes sure that there is a network connection.
    public void myClickHandler(View view) {
        ConnectivityManager connMgr = (ConnectivityManager) 
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
           // new DownloadWebpageTask().doInBackground("");
        }
    }

     /* Uses AsyncTask to create a task away from the main UI thread. This task takes a 
     // URL string and uses it to create an HttpUrlConnection. Once the connection
     // has been established, the AsyncTask downloads the contents of the webpage as
     // an InputStream. Finally, the InputStream is converted into a string, which is
     // displayed in the UI by the AsyncTask's onPostExecute method.
     private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
              
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
           
       }
    }
	
	
	
	
	
	public void go(View view) {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://openexchangerates.org/api/latest.json?app_id=0881c62c0ef746e48df5826b481e574b");
			JSONObject json = new JSONObject();
			try {
				StringEntity se = new StringEntity(json.toString());
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
				post.setEntity(se);
				HttpResponse response = client.execute(post);
				/* Checking response 
				if (response != null) {
					// Get the data in the entity
					InputStream in = response.getEntity().getContent();
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Context context = getApplicationContext();
			Toast.makeText(context, "No availible network found",
					Toast.LENGTH_SHORT).show();
		}

	}
}*/
