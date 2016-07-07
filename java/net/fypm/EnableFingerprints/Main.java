package net.fypm.EnableFingerprints;

import android.content.res.XResources;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    public XC_MethodHook fingerPossible;
    public XC_MethodHook fingerAllow;

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {

        XResources.setSystemWideReplacement("android", "boolean", "mUserHasAuthenticatedSinceBoot", true );
        XResources.setSystemWideReplacement("android", "boolean", "mAlternateUnlockEnabled", true );
        XResources.setSystemWideReplacement("android", "boolean", "mUnlockCompleted", true );

        fingerPossible = new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                /*Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard" + ".KeyguardUpdateMonitor",
                        param.thisObject.getClass().getClassLoader());
                Object KeyguardUpdateMonitorInstance = XposedHelpers.callStaticMethod(KeyguardUpdateMonitor, "getInstance", mContext);
                XposedHelpers.callMethod(KeyguardUpdateMonitorInstance, "setAlternateUnlockEnabled", true);*/
                XposedBridge.log("[EnableFingerprints Debug] Attempting to return true for isUnlockWithFingerPrintPossible");
                param.setResult(true);
                //return true;
            }
        };

        fingerAllow = new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                /*Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard" + ".KeyguardUpdateMonitor",
                        param.thisObject.getClass().getClassLoader());
                Object KeyguardUpdateMonitorInstance = XposedHelpers.callStaticMethod(KeyguardUpdateMonitor, "getInstance", mContext);
                XposedHelpers.callMethod(KeyguardUpdateMonitorInstance, "setAlternateUnlockEnabled", true);*/
                XposedBridge.log("[EnableFingerprints Debug] Attempting to return true for  isUnlockingWithFingerprintAllowed");
                param.setResult(true);
                //return true;
            }
        };

    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equals("com.android.systemui")) {
            try {
                Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard.KeyguardUpdateMonitor$startListeningForFingerprint",
                        lpparam.classLoader);
                XposedHelpers.findAndHookMethod(KeyguardUpdateMonitor, "isUnlockWithFingerPrintPossible", int.class, fingerPossible);
                XposedBridge.log("[EnableFingerprints Debug] Hooked isUnlockWithFingerPrintPossible");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }

            /*try {
                XposedHelpers.findAndHookMethod("com.android.keyguard.KeyguardUpdateMonitor", lpparam.classLoader, "isUnlockingWithFingerprintAllowed", XC_MethodReplacement.returnConstant(true));
                XposedBridge.log("[EnableFingerprints Debug] Replacing  isUnlockingWithFingerprintAllowed");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }*/

            try {
                Class<?> KeyguardUpdateMonitor = XposedHelpers.findClass("com.android.keyguard.KeyguardUpdateMonitor",
                        lpparam.classLoader);
                XposedBridge.hookAllMethods(KeyguardUpdateMonitor, "isUnlockingWithFingerprintAllowed", fingerAllow);
                XposedBridge.log("[EnableFingerprints Debug] Hooked isUnlockingWithFingerprintAllowed");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }

        }
    }
}
