package android.com.jamsand.io.appointmentreminder;

import android.os.Parcel;
import android.os.Parcelable;

public class Appointment implements Parcelable {

    public String name;
    public String type;
    public String monthDate;
    public int dayDate;
    public int yearDate;
    public int hourTime;
    public int minuteTime;
    public String AMorPMTime;

    public Appointment(String name, String type, String monthDate, int dayDate, int yearDate, int hourTime, int minuteTime, String AMorPMTime) {
        this.name = name;
        this.type = type;
        this.monthDate = monthDate;
        this.dayDate = dayDate;
        this.yearDate = yearDate;
        this.hourTime = hourTime;
        this.minuteTime = minuteTime;
        this.AMorPMTime = AMorPMTime;
    }

    protected Appointment(Parcel in) {
        name = in.readString();
        type = in.readString();;
        monthDate = in.readString();;
        dayDate = in.readInt();
        yearDate = in.readInt();
        hourTime = in.readInt();
        minuteTime = in.readInt();
        AMorPMTime = in.readString();;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(monthDate);
        dest.writeInt(dayDate);
        dest.writeInt(yearDate);
        dest.writeInt(hourTime);
        dest.writeInt(minuteTime);
        dest.writeString(AMorPMTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };
}
