package net.fypm.EnableFingerprints;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    public XC_MethodReplacement fingerPossible;
    public XC_MethodReplacement fingerAllow;

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {

        //XResources.setSystemWideReplacement("android", "boolean", "mUserHasAuthenticatedSinceBoot", true );
        //XResources.setSystemWideReplacement("android", "boolean", "mAlternateUnlockEnabled", true );
        //XResources.setSystemWideReplacement("android", "boolean", "mUnlockCompleted", true );

        /*fingerPossible = new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                *//*Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard" + ".KeyguardUpdateMonitor",
                        param.thisObject.getClass().getClassLoader());
                Object KeyguardUpdateMonitorInstance = XposedHelpers.callStaticMethod(KeyguardUpdateMonitor, "getInstance", mContext);
                XposedHelpers.callMethod(KeyguardUpdateMonitorInstance, "setAlternateUnlockEnabled", true);*//*
                XposedBridge.log("[EnableFingerprints Debug] Returning true for isUnlockWithFingerPrintPossible");
                //param.setResult(true);
                return true;
            }
        };

        fingerAllow = new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                *//*Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard" + ".KeyguardUpdateMonitor",
                        param.thisObject.getClass().getClassLoader());
                Object KeyguardUpdateMonitorInstance = XposedHelpers.callStaticMethod(KeyguardUpdateMonitor, "getInstance", mContext);
                XposedHelpers.callMethod(KeyguardUpdateMonitorInstance, "setAlternateUnlockEnabled", true);*//*
                XposedBridge.log("[EnableFingerprints Debug] Returning true for  isUnlockingWithFingerprintAllowed");
                //param.setResult(true);
                return true;
            }
        };*/

    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equals("com.android.systemui")) {
            /*try {
                Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard.KeyguardUpdateMonitor",
                        lpparam.classLoader);
                XposedHelpers.findAndHookMethod(KeyguardUpdateMonitor, "isUnlockWithFingerPrintPossible", int.class, fingerPossible);
                XposedBridge.log("[EnableFingerprints Debug] Hooked isUnlockWithFingerPrintPossible");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }*/

            try {
                /*Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard.KeyguardUpdateMonitor",
                        lpparam.classLoader);*/
                XposedHelpers.findAndHookMethod("com.android.keyguard.KeyguardUpdateMonitor", lpparam.classLoader, "isUnlockWithFingerPrintPossible", int.class, XC_MethodReplacement.returnConstant(true));
                XposedBridge.log("[EnableFingerprints Debug] Replacing  isUnlockWithFingerPrintPossible");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }

            try {
                /*Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard.KeyguardUpdateMonitor",
                        lpparam.classLoader);*/
                XposedHelpers.findAndHookMethod("com.android.keyguard.KeyguardUpdateMonitor", lpparam.classLoader, "isFingerprintDisabled", int.class, XC_MethodReplacement.returnConstant(false));
                XposedBridge.log("[EnableFingerprints Debug] Replacing  isFingerprintDisabled");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }

            try {
                XposedHelpers.findAndHookMethod("com.android.keyguard.KeyguardUpdateMonitor", lpparam.classLoader, "shouldListenForFingerprint", XC_MethodReplacement.returnConstant(true));
                XposedBridge.log("[EnableFingerprints Debug] Replacing  shouldListenForFingerprint");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }

            try {
                XposedHelpers.findAndHookMethod("com.android.keyguard.KeyguardUpdateMonitor", lpparam.classLoader, "isUnlockCompleted", XC_MethodReplacement.returnConstant(true));
                XposedBridge.log("[EnableFingerprints Debug] Replacing  isUnlockCompleted");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }

            try {
                /*Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard.KeyguardUpdateMonitor",
                        lpparam.classLoader);*/
                XposedHelpers.findAndHookMethod("com.android.keyguard.KeyguardUpdateMonitor", lpparam.classLoader, "isUnlockingWithFingerprintAllowed", XC_MethodReplacement.returnConstant(true));
                XposedBridge.log("[EnableFingerprints Debug] Replacing  isUnlockingWithFingerprintAllowed");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }

            /*try {
                Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard.KeyguardUpdateMonitor",
                        lpparam.classLoader);
                XposedBridge.hookAllMethods(KeyguardUpdateMonitor, "isUnlockingWithFingerprintAllowed", fingerAllow);
                XposedBridge.log("[EnableFingerprints Debug] Hooked isUnlockingWithFingerprintAllowed");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }*/

        }
    }
}
