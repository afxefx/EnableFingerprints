package net.fypm.EnableFingerprints;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equals("com.android.systemui")) {
            try {
                XposedHelpers.findAndHookMethod("com.android.keyguard.KeyguardUpdateMonitor", lpparam.classLoader, "isUnlockWithFingerPrintPossible", int.class, XC_MethodReplacement.returnConstant(true));
                XposedBridge.log("[EnableFingerprints Debug] Replacing  isUnlockWithFingerPrintPossible");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }

            try {
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
                XposedHelpers.findAndHookMethod("com.android.keyguard.KeyguardUpdateMonitor", lpparam.classLoader, "isUnlockingWithFingerprintAllowed", XC_MethodReplacement.returnConstant(true));
                XposedBridge.log("[EnableFingerprints Debug] Replacing  isUnlockingWithFingerprintAllowed");
            } catch (Throwable e) {
                XposedBridge.log(e);
            }
        }
    }
}
