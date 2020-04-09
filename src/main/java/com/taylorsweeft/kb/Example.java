package com.taylorsweeft.kb;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import static com.sun.jna.platform.win32.WinUser.*;

/**
 * @author: taylorsweeft13
 * @create: 2020/04/07
 * @version: 1.0
 **/
public class Example {

    public static void main(String[] args) throws InterruptedException {
        User32 instance = User32.INSTANCE;
        Thread.sleep(1000);
        WinDef.HWND hWnd = instance.GetForegroundWindow();
        char[] title = new char[150];
        instance.GetWindowText(hWnd, title, title.length);
        WinDef.LPARAM nullLPARAM = new WinDef.LPARAM();
//        关闭窗口
//        instance.PostMessage(hWnd, WM_CLOSE, null, null);
//        press SPACE
//        Keyboard.pressKey(Key.SPACE);
        instance.SendMessage(hWnd, WM_CHAR, new WPARAM(Key.K1), new LPARAM());

        instance.PostMessage(hWnd, WM_KEYDOWN, new WPARAM(Key.K2), new LPARAM());
        instance.PostMessage(hWnd, WM_KEYUP, new WPARAM(Key.K2), new LPARAM());
        System.out.println(title);

    }

}
