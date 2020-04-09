package com.taylorsweeft.kb;

import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import static com.sun.jna.platform.win32.WinUser.*;

/**
 * @author: taylorsweeft13
 * @create: 2020/04/07
 * @version: 1.0
 **/
public class Keyboard {

    public static final int KEYDOWN_EVENT = 0;
    public static final int KEYUP_EVENT = 2;

    /**
     * Check if a key is pressed.
     *
     * @param key Key-code. For example: <i>Key.SPACE </i>
     * @return {@code true} if key is down. False otherwise.
     */
    public static boolean isKeyDown(int key) {
        short state = User32.INSTANCE.GetAsyncKeyState(key);
        return (0x1 & (state >> (Short.SIZE - 1))) != 0;
    }

    /**
     * Sends a key-down input followed by a key-up input for the given character
     * value c.
     *
     * @param key
     */
    public static void pressKey(int key) {
        WinUser.INPUT input = new INPUT();
        input.type = new WinDef.DWORD(INPUT.INPUT_KEYBOARD);
        input.input.setType("ki");
        input.input.ki.wScan = new WORD(0);
        input.input.ki.time = new DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        input.input.ki.wVk = new WORD(key);
        input.input.ki.dwFlags = new DWORD(KEYDOWN_EVENT);
        User32.INSTANCE.SendInput(new DWORD(1), (INPUT[]) input.toArray(1), input.size());
        input.input.ki.wVk = new WORD(key);
        input.input.ki.dwFlags = new DWORD(KEYUP_EVENT);
        User32.INSTANCE.SendInput(new DWORD(1), (INPUT[]) input.toArray(1), input.size());
    }

    /**
     * Sends a key-down input for the given character value c.
     *
     * @param key
     */
    public static void sendKeyDown(int key) {
        INPUT input = new INPUT();
        input.type = new DWORD(INPUT.INPUT_KEYBOARD);
        input.input.setType("ki");
        input.input.ki.wScan = new WORD(0);
        input.input.ki.time = new DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        input.input.ki.wVk = new WORD(key);
        input.input.ki.dwFlags = new DWORD(KEYDOWN_EVENT);
        User32.INSTANCE.SendInput(new DWORD(1), (INPUT[]) input.toArray(1), input.size());
    }

    /**
     * Sends a key-up input for the given character value c.
     *
     * @param key
     */
    public static void sendKeyUp(int key) {
        INPUT input = new INPUT();
        input.type = new DWORD(INPUT.INPUT_KEYBOARD);
        input.input.setType("ki");
        input.input.ki.wScan = new WORD(0);
        input.input.ki.time = new DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        input.input.ki.wVk = new WORD(key);
        input.input.ki.dwFlags = new DWORD(KEYUP_EVENT);
        User32.INSTANCE.SendInput(new DWORD(1), (INPUT[]) input.toArray(1), input.size());
    }
}
