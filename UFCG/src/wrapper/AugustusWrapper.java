package wrapper;

import envs.config.PathConfig;
import envs.toolkit.ExecHandler;
import envs.toolkit.Shell;

public class AugustusWrapper extends ExecHandler {
	public AugustusWrapper() {
		super.init(PathConfig.AugustusPath);
	}
	
	void setCfg() {
		addArg("--optCfgFile=" + PathConfig.AugustusConfig);
	}
	void setStart(int pst) {
		addArg("--predictionStart=" + String.valueOf(pst));
	}
	void setEnd(int ped) {
		addArg("--predictionEnd=" + String.valueOf(ped));
	}
	void setPrfl(String prfl) {
		addArg("--proteinprofile=" + prfl);
	}
	void setCtgPath(String ctgPath) {
		addArg(ctgPath);
	}
	void setOutPath(String outPath) {
		addArg(">", outPath);
	}
	
	public static void runAugustus(String ctgPath, int pst, int ped, String prfl, String outPath) {
		AugustusWrapper aug = new AugustusWrapper();
		aug.setCfg();
		aug.setStart(pst);
		aug.setEnd(ped);
		aug.setPrfl(prfl);
		aug.setCtgPath(ctgPath);
		aug.setOutPath(outPath);
		aug.exec();
	}
	
	// solve dependency
	public static boolean solve() {
		String[] cmd = {"/bin/bash", "-c",
				PathConfig.AugustusPath + " /dev/null 2>&1"};
		String[] raw = Shell.exec(cmd);
		if(raw[0].contains("not found")) return false;
		
		int error_loc = 0;
		for(; !raw[error_loc].contains("ERROR"); error_loc++) {
			if(error_loc + 1 == raw.length) return false; 
		}
		return true;
	}
	
	public static boolean checkConfigPath() {
		String[] cmd = {"/bin/bash", "-c",
				PathConfig.AugustusPath + " --species=rhizopus_oryzae /dev/null 2>&1"};
		String[] raw = Shell.exec(cmd);
		
		int error_loc = 0;
		for(; !raw[error_loc].contains("ERROR"); error_loc++);
		if(raw[++error_loc].contains("AUGUSTUS_CONFIG_PATH")) return false;
		return true;
	}
	
	public static boolean checkConfigFile() {
		String[] cmd = {"/bin/bash", "-c",
				PathConfig.AugustusPath + " --optCfgFile=" + PathConfig.AugustusConfig + " /dev/null 2>&1"};
		String[] raw = Shell.exec(cmd);
		
		int error_loc = 0;
		for(; !raw[error_loc].contains("ERROR"); error_loc++);
		if(raw[++error_loc].contains("No sequences found")) return true;
		return false;
	}
}
