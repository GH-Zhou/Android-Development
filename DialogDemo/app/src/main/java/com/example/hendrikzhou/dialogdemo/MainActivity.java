package com.example.hendrikzhou.dialogdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                showNormalDialog1();
                break;
            case R.id.btn2:
                showNormalDialog2();
                break;
            case R.id.btn3:
                showListDialog();
                break;
            case R.id.btn4:
                showSingleChoiceDialog();
                break;
            case R.id.btn5:
                showMultiChoiceDialog();
                break;
            case R.id.btn6:
                showWaitingDialog();
                break;
            case R.id.btn7:
                showProgressDialog();
                break;
            case R.id.btn8:
                showInputDialog();
                break;
            case R.id.btn9:
                // 1. Customize a class, inherited from class Dialog,
                // and call setContentView while constructing it
                // 2. Set the style of customized dialog (Don't show the title or background)
                // 3. Create OnClickListener to the buttons

                // Instantialize the customized dialog, and display
                MyDialog dialog = new MyDialog(this);
                dialog.show();
                break;
            case R.id.btn10:
                showArrayDialog();
                break;
            default:
                Toast.makeText(MainActivity.this,
                                "まだ実装されていない",
                                Toast.LENGTH_SHORT)
                        .show();
        }
    }

    // 1. Using AlertDialog Builder
    public void showNormalDialog1() {
        // Constructor of AlertDialog is protected.
        // Thus it's unavailable outside the package, and we'll need to use Builder
        // AlertDialog = dialog = new AlertDialog(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the title and message
        builder.setTitle("ダイアログ１")
                .setMessage("あなたは、このアプリを終了してもよろしいですか？")
                .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("いいえ", null)
                .setNeutralButton("閉じる", null)
                .show(); // including builder.create()
    }

    // 2. Using AlertDialog
    public void showNormalDialog2() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("ダイアログ２");
        dialog.setMessage("あなたは、どの空港にいきたいですか");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,
                        "CKG",
                        new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,
                                "「重慶空港」を選びました",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                        "XIY",
                        new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                        "CTU",
                        new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    public void showListDialog() {
        final String[] items = {"アイテム１", "アイテム２", "アイテム３", "アイテム４"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("選んでください")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,
                                "あなたは、" + items[i] + "を選びました",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    int selection = 0;
    public void showSingleChoiceDialog() {
        final String[] airports = {"CKG", "XIY", "CTU", "LAX"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("好きな空港を選んでください")
                .setSingleChoiceItems(airports, selection, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selection = i;
                    }
                })
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Different i from that in OnClickListener of setSingleChoiceItems.
                        Toast.makeText(MainActivity.this,
                                        "あなたが好きな空港は：" + airports[selection],
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .show();

    }

    public void showMultiChoiceDialog() {
        final String[] anime = {"ダリフラ", "進撃の巨人", "ギルクラ", "甲鉄城のカバネリ", "ガンダムUC", "七つの大罪"};
        final boolean[] checked = {true, true, false, false, true, false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("好きなアニメを選んでください")
                .setMultiChoiceItems(anime, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        checked[i] = b;
                        Log.e("Log", anime[i] + " " + b);
                    }
                })
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String msg = "あなたが好きなアニメは：";
                        for (int idx = 0; idx < anime.length; idx ++) {
                            if (checked[idx]) {
                                msg += anime[idx] + " ";
                            }
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void showWaitingDialog() {
        // ProgressDialog is deprecated from API 26
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("待ちダイアログ");
        dialog.setMessage("お待ちください");
        dialog.setCancelable(true); // default: true
        dialog.show();
//        dialog.dismiss(); // let dialog disappear
    }

    public void showProgressDialog() {
        // ProgressDialog is deprecated from API 26
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("ダウンロード中...");
        dialog.setMessage("お待ちください");
        dialog.setIndeterminate(false); // Ambiguous progress
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 1; i <= 100; i ++) {
                    dialog.setProgress(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        }.start();
    }

    public void showInputDialog() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("入力ダイアログ")
                .setView(editText)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,
                                        "あなたが入力したのは：" + editText.getText().toString(),
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .show();
    }

    public void showArrayDialog() {
        final String[] items = {"Java", "MySQL", "Android", "HTML", "C", "JavaScript"};
        // Param1: Context, Param2: resource (R.layout.XXX), Param3: Data Source
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items);

        // Param1: Context, Param2: resource (R.layout.XXX), Param3: textViewId
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.array_item_layout, R.id.item_txt, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("選んでください")
                // Param1: Adapter Object (customize the display style of data), Param2: Listener
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,
                                        "あなたは、" + items[i] + "を選びました",
                                        Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
