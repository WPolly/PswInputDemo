# PswInputView
简单灵活的数字密码输入框控件

使用简便，高度可定制化。
## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'com.github.WPolly:PswInputView:v1.0'
}
```

## Usage

```
 <com.practice.lishan.pswinputview.PswInputView
            android:id="@+id/piv_default"
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:layout_margin="20dp"/>
    
        <com.practice.lishan.pswinputview.PswInputView
            android:id="@+id/piv_asset_protect"
            android:layout_width="match_parent"
            android:layout_height="120dp"
            android:layout_margin="20dp"
            app:pswCount="8"
            app:borderColor="@color/colorPrimaryDark"
            app:dividerColor="@color/colorPrimary"
            app:dividerStrokeWidth="0.8dp"
            app:borderCornerRadius="8dp"
            app:pswDotRadius="6dp"
            app:pswDotColor="@color/colorBlue"/>
 ```

