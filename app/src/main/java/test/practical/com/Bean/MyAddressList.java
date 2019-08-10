
package test.practical.com.Bean;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class MyAddressList {

    @SerializedName("Address")
    private String address;
    @SerializedName("AddressId")
    private String addressId;
    @SerializedName("AddressType")
    private String addressType;
    @SerializedName("Latitude")
    private String latitude;
    @SerializedName("Longitude")
    private String longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
