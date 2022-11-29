package com.example.lovablelumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout1, layout2, layout3;
    private TextView name1View, name2View;
    private EditText name1Input, name2Input;
    private Spinner dropdown1, dropdown2;


    private int mDefaultColor;
    private Button colorButton1, colorButton2;
    private SeekBar colorSlider1, colorSlider2;

    private Button buttonTab1, buttonTab2;

    private ImageView[] imageViews = new ImageView[10];

    private String[] images = {"circle_dark_walnut", "circle_ebony", "circle_golden_oak", "circle_grey_oak",
            "circle_light_walnut", "circle_natural", "circle_red_mahogany", "circle_special_walnut",
            "circle_whitewash", "circle_worn_navy"};

    Typeface simplicity, twoIsBetter, canvas, rockPillar, brooklyn, hipster, beneath, pepperSky, marketFresh,
            stoneWalls, seaLevel, wonderland, rainbow, playlist, bambooForest, hotCocoa, southernCharm,
            cupcake, luna, helicopterRide, wildflower, adventure, lighthouse, lemonade, milkyWay, breathe,
            bubbleBath, satisfied, beStill, bromello, hickoryJack, lavenderSky, goodVibes, sunlitCanal,
            mountainGLow, seasideWonder, madinaScript, sunshine, magnolia, happySoul, unicorns, whiteWaterfall,
            bohoScript, beMagical, peaceful, daydreams, namaste, stardust, wildChild;


    String currentStain = "circle_grey_oak";

    //drag and drop stuff
    private RelativeLayout relativeLayout;
    private ViewGroup rootLayout;
    private int _xDelta;
    private int _yDelta;

    private ConstraintLayout root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View view = findViewById(R.id.main_layout);

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        //| View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        view.setSystemUiVisibility(flags);

        //Layouts
        layout1 = findViewById(R.id.layoutTab1);
        layout2 = findViewById(R.id.layoutTab2);
        layout3 = findViewById(R.id.layoutTab3);

        //textViews
        name1View = (TextView) findViewById(R.id.name1);
        name2View = (TextView) findViewById(R.id.name2);

        //editTexts
        name1Input = (EditText) findViewById(R.id.editName1);
        name2Input = (EditText) findViewById(R.id.editName2);

        //spinners
        dropdown1 = (Spinner) findViewById(R.id.spinner);
        dropdown2 = (Spinner) findViewById(R.id.spinner2);

        //tab buttons
        buttonTab1 = (Button) findViewById(R.id.buttonTab1);
        buttonTab2 = (Button) findViewById(R.id.buttonTab2);

        //all the imageViews
        /*
        String temp;
        for (int i = 0; i < images.length; i++) {
            temp = images[i];
            int resID = getResources().getIdentifier(temp, "id", getPackageName());
            imageViews[i] = (ImageView) findViewById(resID);
        }
        */

        imageViews[0] = (ImageView) findViewById(R.id.circle_red_mahogany);
        imageViews[1] = (ImageView) findViewById(R.id.circle_dark_walnut);
        imageViews[2] = (ImageView) findViewById(R.id.circle_natural);
        imageViews[3] = (ImageView) findViewById(R.id.circle_grey_oak);
        imageViews[4] = (ImageView) findViewById(R.id.circle_light_walnut);
        imageViews[5] = (ImageView) findViewById(R.id.circle_special_walnut);
        imageViews[6] = (ImageView) findViewById(R.id.circle_worn_navy);
        imageViews[7] = (ImageView) findViewById(R.id.circle_whitewash);
        imageViews[8] = (ImageView) findViewById(R.id.circle_ebony);
        imageViews[9] = (ImageView) findViewById(R.id.circle_golden_oak);

        //fonts
        {
            //fonts
            simplicity = Typeface.createFromAsset(getAssets(), "fonts/simplicity.ttf");
            twoIsBetter = Typeface.createFromAsset(getAssets(), "fonts/KGTwoisBetterThanOne.ttf");
            canvas = Typeface.createFromAsset(getAssets(), "fonts/TheSalvador-Condensed (Canvas).otf");
            rockPillar = Typeface.createFromAsset(getAssets(), "fonts/Happy-Camper-Regular (Rock Pillar).ttf");
            brooklyn = Typeface.createFromAsset(getAssets(), "fonts/Hastoler Regular (Brooklyn).otf");
            hipster = Typeface.createFromAsset(getAssets(), "fonts/HipsterishFontNormal (Hipster).ttf");
            beneath = Typeface.createFromAsset(getAssets(), "fonts/KGBeneathYourBeautifulChunk (Beneath).ttf");
            pepperSky = Typeface.createFromAsset(getAssets(), "fonts/KGRiseUP (Pepper Sky).ttf");
            marketFresh = Typeface.createFromAsset(getAssets(), "fonts/LemonMilk (Market Fresh).otf");
            stoneWalls = Typeface.createFromAsset(getAssets(), "fonts/NorthernLights-CAPS (Stone Walls).ttf");
            seaLevel = Typeface.createFromAsset(getAssets(), "fonts/NORTHWEST-B-DEMO (Sea Level).otf");
            wonderland = Typeface.createFromAsset(getAssets(), "fonts/OstrichSans-Heavy (Wonderland).otf");
            rainbow = Typeface.createFromAsset(getAssets(), "fonts/ostrich sans inline-regular (Rainbow).otf");
            playlist = Typeface.createFromAsset(getAssets(), "fonts/Playlist Caps Solid (Playlist).ttf");
            bambooForest = Typeface.createFromAsset(getAssets(), "fonts/SkipLegDay (Bamboo Forest).ttf");
            hotCocoa = Typeface.createFromAsset(getAssets(), "fonts/Boho Sans W00 Regular (Hot Cocoa).ttf");
            southernCharm = Typeface.createFromAsset(getAssets(), "fonts/MissMagnolia-Uppercase (Southern Charm).ttf");
            cupcake = Typeface.createFromAsset(getAssets(), "fonts/Chica Gogo NF W01 Regular (Cupcake).otf");
            luna = Typeface.createFromAsset(getAssets(), "fonts/Luna.ttf");
            helicopterRide = Typeface.createFromAsset(getAssets(), "fonts/BEARHUGS-Fixed (Helicopter Ride).ttf");
            wildflower = Typeface.createFromAsset(getAssets(), "fonts/Summer Lemonade Sans Two (Wildflower).ttf");
            adventure = Typeface.createFromAsset(getAssets(), "fonts/212 Moon Child Sans (Adventure).otf");
            lighthouse = Typeface.createFromAsset(getAssets(), "fonts/Montebello-Rounded (Lighthouse).ttf");
            lemonade = Typeface.createFromAsset(getAssets(), "fonts/ThirstyScriptRoughW01-One-Solid (Lemonade).ttf");
            milkyWay = Typeface.createFromAsset(getAssets(), "fonts/KGAlwaysAGoodTime (Milky Way).ttf");
            breathe = Typeface.createFromAsset(getAssets(), "fonts/Montebello-Script (Breathe).ttf");
            bubbleBath = Typeface.createFromAsset(getAssets(), "fonts/Try Happiness Demo (Bubble Bath).ttf");
            satisfied = Typeface.createFromAsset(getAssets(), "fonts/KGSatisfiedScript (Satistfied).ttf");
            beStill = Typeface.createFromAsset(getAssets(), "fonts/learning_curve_regular_ot_tt (Be Still).ttf");
            bromello = Typeface.createFromAsset(getAssets(), "fonts/bromello.ttf");
            hickoryJack = Typeface.createFromAsset(getAssets(), "fonts/Hickory Jack.ttf");
            lavenderSky = Typeface.createFromAsset(getAssets(), "fonts/Amarillo (Lavender Sky).ttf");
            goodVibes = Typeface.createFromAsset(getAssets(), "fonts/Naila (Good Vibes).otf");
            sunlitCanal = Typeface.createFromAsset(getAssets(), "fonts/Autumn in November (Sunlit Canal).ttf");
            mountainGLow = Typeface.createFromAsset(getAssets(), "fonts/beyond_the_mountains (Mountain Glow).ttf");
            seasideWonder = Typeface.createFromAsset(getAssets(), "fonts/King-Basil-Lite (Seaside Wonder).otf");
            madinaScript = Typeface.createFromAsset(getAssets(), "fonts/Madina Clean (Madina).ttf");
            sunshine = Typeface.createFromAsset(getAssets(), "fonts/MandaScript-Regular (Sunshine).ttf");
            magnolia = Typeface.createFromAsset(getAssets(), "fonts/MissMagnolia-Script-Lowercase.ttf");
            happySoul = Typeface.createFromAsset(getAssets(), "fonts/Playlist-Script-solid(er) (Happy Soul).ttf");
            unicorns = Typeface.createFromAsset(getAssets(), "fonts/Shorelines Script Bold (Unicorns).otf");
            whiteWaterfall = Typeface.createFromAsset(getAssets(), "fonts/Sugar Plums (White Waterfall).ttf");
            bohoScript = Typeface.createFromAsset(getAssets(), "fonts/Boho Script W01 Bold (Boho Script).otf");
            beMagical = Typeface.createFromAsset(getAssets(), "fonts/Summer Lemonade Script (Be Magical).ttf");
            peaceful = Typeface.createFromAsset(getAssets(), "fonts/Halimun (Peaceful).ttf");
            daydreams = Typeface.createFromAsset(getAssets(), "fonts/Stea Font (Day Dreams).otf");
            namaste = Typeface.createFromAsset(getAssets(), "fonts/Prestige Signature Script - Demo (Namaste).ttf");
            stardust = Typeface.createFromAsset(getAssets(), "fonts/Red Velvet - Demo (Star Dust).ttf");
            wildChild = Typeface.createFromAsset(getAssets(), "fonts/strudeFixed (Wild Child).ttf");

        }

        name1Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name1View.setText(name1Input.getText());
                name1View.setWidth(799);

                TextViewCompat.setAutoSizeTextTypeWithDefaults(name1View,
                        TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name2Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name2View.setText(name2Input.getText());
                TextViewCompat.setAutoSizeTextTypeWithDefaults(name2View,
                        TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //Text Color
        mDefaultColor = ContextCompat.getColor(MainActivity.this, R.color.colorPrimary);
        colorButton1 = (Button) findViewById(R.id.button);
        colorButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker(1);
            }
        });

        colorButton2 = (Button) findViewById(R.id.button2);
        colorButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker(2);
            }
        });

        colorSlider1 = (SeekBar) findViewById(R.id.seekBar);
        colorSlider1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(progress) {
                    case 0:
                        name1View.setTextColor(Color.GRAY);
                        break;
                    case 1:
                        name1View.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        name1View.setTextColor(Color.RED);
                        break;
                    case 3:
                        name1View.setTextColor(Color.YELLOW);
                        break;
                    case 4:
                        name1View.setTextColor(Color.GREEN);
                        break;
                    case 5:
                        name1View.setTextColor(Color.BLUE);
                        break;
                    case 6:
                        name1View.setTextColor(Color.CYAN);
                        break;
                    case 7:
                        name1View.setTextColor(Color.MAGENTA);
                        break;
                    case 8:
                        name1View.setTextColor(Color.WHITE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        colorSlider2 = (SeekBar) findViewById(R.id.seekBar2);
        colorSlider2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(progress) {
                    case 0:
                        name2View.setTextColor(Color.GRAY);
                        break;
                    case 1:
                        name2View.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        name2View.setTextColor(Color.RED);
                        break;
                    case 3:
                        name2View.setTextColor(Color.YELLOW);
                        break;
                    case 4:
                        name2View.setTextColor(Color.GREEN);
                        break;
                    case 5:
                        name2View.setTextColor(Color.BLUE);
                        break;
                    case 6:
                        name2View.setTextColor(Color.CYAN);
                        break;
                    case 7:
                        name2View.setTextColor(Color.MAGENTA);
                        break;
                    case 8:
                        name2View.setTextColor(Color.WHITE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        root = (ConstraintLayout) findViewById(R.id.main_layout);

        //drag and drop
        rootLayout = (ViewGroup) findViewById(R.id.relativeLayout);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(800, 200);
        layoutParams.leftMargin = 300;
        layoutParams.topMargin = 450;
        name1View.setLayoutParams(layoutParams);
        name1View.setOnTouchListener(getTouchListener());

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(800, 200);
        layoutParams2.leftMargin = 300;
        layoutParams2.topMargin = 800;
        name2View.setLayoutParams(layoutParams2);
        name2View.setOnTouchListener(getTouchListener());




        /*

        // detect if the soft keyboard is open
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = root.getRootView().getHeight() - root.getHeight();

                if (heightDiff > 100) {
                    hideSystemUI();
                    //Toast.makeText(MainActivity.this, "keyboard", Toast.LENGTH_SHORT).show();
                } else {
                    showSystemUI();
                    //Toast.makeText(MainActivity.this, "no keyboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        */



        getWindow().getDecorView().setSystemUiVisibility(flags);

        // Code below is to handle presses of Volume up or Volume down.
        // Without this, after pressing volume buttons, the navigation bar will
        // show up and won't hide
        final View decorView = getWindow().getDecorView();
        decorView
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                {

                    @Override
                    public void onSystemUiVisibilityChange(int visibility)
                    {
                        if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                        {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });


    }

    public void openColorPicker(final int textChoice)
    {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;

                if (textChoice == 1)
                    name1View.setTextColor(mDefaultColor);
                if (textChoice == 2)
                    name2View.setTextColor(mDefaultColor);
            }
        });
        colorPicker.show();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                switch(selectedItem) {
                    case "Simplicity":
                        name1View.setTypeface(simplicity);
                        break;
                    case "Two is Better":
                        name1View.setTypeface(twoIsBetter);
                        break;
                    case "Canvas":
                        name1View.setTypeface(canvas);
                        break;
                    case "Rock Pillar":
                        name1View.setTypeface(rockPillar);
                        break;
                    case "Brooklyn":
                        name1View.setTypeface(brooklyn);
                        break;
                    case "Hipster":
                        name1View.setTypeface(hipster);
                        break;
                    case "Beneath":
                        name1View.setTypeface(beneath);
                        break;
                    case "Pepper Sky":
                        name1View.setTypeface(pepperSky);
                        break;
                    case "Market Fresh":
                        name1View.setTypeface(marketFresh);
                        break;
                    case "Stone Walls":
                        name1View.setTypeface(stoneWalls);
                        break;
                    case "Sea Level":
                        name1View.setTypeface(seaLevel);
                        break;
                    case "Wonderland":
                        name1View.setTypeface(wonderland);
                        break;
                    case "Rainbow":
                        name1View.setTypeface(rainbow);
                        break;
                    case "Playlist":
                        name1View.setTypeface(playlist);
                        break;
                    case "Bamboo Forest":
                        name1View.setTypeface(bambooForest);
                        break;
                    case "Hot Cocoa":
                        name1View.setTypeface(hotCocoa);
                        break;
                    case "Southern Charm":
                        name1View.setTypeface(southernCharm);
                        break;
                    case "Cupcake":
                        name1View.setTypeface(cupcake);
                        break;
                    case "Luna":
                        name1View.setTypeface(luna);
                        break;
                    case "Helicopter Ride":
                        name1View.setTypeface(helicopterRide);
                        break;
                    case "Wildflower":
                        name1View.setTypeface(wildflower);
                        break;
                    case "Lighthouse":
                        name1View.setTypeface(lighthouse);
                        break;
                    case "Lemonade":
                        name1View.setTypeface(lemonade);
                        break;
                    case "Milky Way":
                        name1View.setTypeface(milkyWay);
                        break;
                    case "Breathe":
                        name1View.setTypeface(breathe);
                        break;
                    case "Bubble Bath":
                        name1View.setTypeface(bubbleBath);
                        break;
                    case "Satisfied":
                        name1View.setTypeface(satisfied);
                        break;
                    case "Be Still":
                        name1View.setTypeface(beStill);
                        break;
                    case "Bromello":
                        name1View.setTypeface(bromello);
                        break;
                    case "Hickory Jack":
                        name1View.setTypeface(hickoryJack);
                        break;
                    case "Lavender Sky":
                        name1View.setTypeface(lavenderSky);
                        break;
                    case "Good Vibes":
                        name1View.setTypeface(goodVibes);
                        break;
                    case "Sunlit Canal":
                        name1View.setTypeface(sunlitCanal);
                        break;
                    case "Mountain Glow":
                        name1View.setTypeface(mountainGLow);
                        break;
                    case "Seaside Wonder":
                        name1View.setTypeface(seasideWonder);
                        break;
                    case "Madina Script":
                        name1View.setTypeface(madinaScript);
                        break;
                    case "Sunshine":
                        name1View.setTypeface(sunshine);
                        break;
                    case "Magnolia":
                        name1View.setTypeface(magnolia);
                        break;
                    case "Happy Soul":
                        name1View.setTypeface(happySoul);
                        break;
                    case "Unicorns":
                        name1View.setTypeface(unicorns);
                        break;
                    case "White Waterfall":
                        name1View.setTypeface(whiteWaterfall);
                        break;
                    case "Boho Script":
                        name1View.setTypeface(bohoScript);
                        break;
                    case "Be Magical":
                        name1View.setTypeface(beMagical);
                        break;
                    case "Peaceful":
                        name1View.setTypeface(peaceful);
                        break;
                    case "Daydreams":
                        name1View.setTypeface(daydreams);
                        break;
                    case "Namaste":
                        name1View.setTypeface(namaste);
                        break;
                    case "Stardust":
                        name1View.setTypeface(stardust);
                        break;
                    case "Wild Child":
                        name1View.setTypeface(wildChild);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem2 = parent.getItemAtPosition(position).toString();

                switch(selectedItem2) {
                    case "Simplicity":
                        name2View.setTypeface(simplicity);
                        break;
                    case "Two is Better":
                        name2View.setTypeface(twoIsBetter);
                        break;
                    case "Canvas":
                        name2View.setTypeface(canvas);
                        break;
                    case "Rock Pillar":
                        name2View.setTypeface(rockPillar);
                        break;
                    case "Brooklyn":
                        name2View.setTypeface(brooklyn);
                        break;
                    case "Hipster":
                        name2View.setTypeface(hipster);
                        break;
                    case "Beneath":
                        name2View.setTypeface(beneath);
                        break;
                    case "Pepper Sky":
                        name2View.setTypeface(pepperSky);
                        break;
                    case "Market Fresh":
                        name2View.setTypeface(marketFresh);
                        break;
                    case "Stone Walls":
                        name2View.setTypeface(stoneWalls);
                        break;
                    case "Sea Level":
                        name2View.setTypeface(seaLevel);
                        break;
                    case "Wonderland":
                        name2View.setTypeface(wonderland);
                        break;
                    case "Rainbow":
                        name2View.setTypeface(rainbow);
                        break;
                    case "Playlist":
                        name2View.setTypeface(playlist);
                        break;
                    case "Bamboo Forest":
                        name2View.setTypeface(bambooForest);
                        break;
                    case "Hot Cocoa":
                        name2View.setTypeface(hotCocoa);
                        break;
                    case "Southern Charm":
                        name2View.setTypeface(southernCharm);
                        break;
                    case "Cupcake":
                        name2View.setTypeface(cupcake);
                        break;
                    case "Luna":
                        name2View.setTypeface(luna);
                        break;
                    case "Helicopter Ride":
                        name2View.setTypeface(helicopterRide);
                        break;
                    case "Wildflower":
                        name2View.setTypeface(wildflower);
                        break;
                    case "Lighthouse":
                        name2View.setTypeface(lighthouse);
                        break;
                    case "Lemonade":
                        name2View.setTypeface(lemonade);
                        break;
                    case "Milky Way":
                        name2View.setTypeface(milkyWay);
                        break;
                    case "Breathe":
                        name2View.setTypeface(breathe);
                        break;
                    case "Bubble Bath":
                        name2View.setTypeface(bubbleBath);
                        break;
                    case "Satisfied":
                        name2View.setTypeface(satisfied);
                        break;
                    case "Be Still":
                        name2View.setTypeface(beStill);
                        break;
                    case "Bromello":
                        name2View.setTypeface(bromello);
                        break;
                    case "Hickory Jack":
                        name2View.setTypeface(hickoryJack);
                        break;
                    case "Lavender Sky":
                        name2View.setTypeface(lavenderSky);
                        break;
                    case "Good Vibes":
                        name2View.setTypeface(goodVibes);
                        break;
                    case "Sunlit Canal":
                        name2View.setTypeface(sunlitCanal);
                        break;
                    case "Mountain Glow":
                        name2View.setTypeface(mountainGLow);
                        break;
                    case "Seaside Wonder":
                        name2View.setTypeface(seasideWonder);
                        break;
                    case "Madina Script":
                        name2View.setTypeface(madinaScript);
                        break;
                    case "Sunshine":
                        name2View.setTypeface(sunshine);
                        break;
                    case "Magnolia":
                        name2View.setTypeface(magnolia);
                        break;
                    case "Happy Soul":
                        name2View.setTypeface(happySoul);
                        break;
                    case "Unicorns":
                        name2View.setTypeface(unicorns);
                        break;
                    case "White Waterfall":
                        name2View.setTypeface(whiteWaterfall);
                        break;
                    case "Boho Script":
                        name2View.setTypeface(bohoScript);
                        break;
                    case "Be Magical":
                        name2View.setTypeface(beMagical);
                        break;
                    case "Peaceful":
                        name2View.setTypeface(peaceful);
                        break;
                    case "Daydreams":
                        name2View.setTypeface(daydreams);
                        break;
                    case "Namaste":
                        name2View.setTypeface(namaste);
                        break;
                    case "Stardust":
                        name2View.setTypeface(stardust);
                        break;
                    case "Wild Child":
                        name2View.setTypeface(wildChild);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Tab Switchers
    public void switchToTab1 (View view)
    {
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout1.setVisibility(View.VISIBLE);
        buttonTab1.setTypeface(Typeface.DEFAULT_BOLD);
        buttonTab2.setTypeface(Typeface.DEFAULT);
    }

    public void switchToTab2 (View view)
    {
        layout1.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        buttonTab1.setTypeface(Typeface.DEFAULT);
        buttonTab2.setTypeface(Typeface.DEFAULT_BOLD);
    }


    public void changeStain(View view) {

        String stain = view.getTag().toString();
        int oldStain = 3;
        int newStain = 0;
        if (stain != currentStain) {
            for (int i = 0; i < imageViews.length; i++) {
                if (imageViews[i].getTag().toString() == currentStain) {
                    oldStain = i;
                }
                if (imageViews[i].getTag().toString() == stain) {
                    newStain = i;
                }
            }
            imageViews[newStain].setVisibility(View.VISIBLE);
            imageViews[oldStain].setVisibility(View.GONE);
            currentStain = stain;
        }
    }

    private final TouchListenerImpl getTouchListener() {
        int min = 50;
        return new TouchListenerImpl(min, min);
    }


    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        //View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //View.SYSTEM_UI_FLAG_LOW_PROFILE |
                        View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }

    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_VISIBLE |
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        );
    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

    
    
}
