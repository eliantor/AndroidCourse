package corso.samples.os;

import android.os.Parcel;
import android.os.Parcelable;

public class OperationAndResult implements Parcelable{
	
	public final String operation;
	public final double result;
	
	public OperationAndResult(String operation,double result) {
		this.operation = operation;
		this.result = result;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(operation);
		dest.writeDouble(result);
	}
	public final static Parcelable.Creator<OperationAndResult> CREATOR = new Creator<OperationAndResult>() {
		@Override
		public OperationAndResult[] newArray(int size) {
			return new OperationAndResult[size];
		}
		
		@Override
		public OperationAndResult createFromParcel(Parcel source) {
			
			return new OperationAndResult(source.readString(),source.readDouble());
		}
	};
	
	
}
