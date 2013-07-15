package corso.samples.concurrency;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SimpleGenericService extends Service{
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
}
