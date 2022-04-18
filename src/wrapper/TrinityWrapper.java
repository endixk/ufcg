package wrapper;

import envs.config.PathConfig;
import envs.toolkit.ExecHandler;
import envs.toolkit.Shell;

public class TrinityWrapper extends ExecHandler {
	public TrinityWrapper() {
		super.init(PathConfig.TrinityPath);
	}
	
	void setSeqType(String seqType) {
		super.addArg("--seqType", seqType);
	}
	
	void setLeft(String file) {
		super.addArg("--left", file);
	}
	
	void setRight(String file) {
		super.addArg("--right", file);
	}
	
	void setSingle(String file) {
		super.addArg("--single", file);
	}
	
	void setCPU(int cpu) {
		super.addArg("--CPU", String.valueOf(cpu));
	}
	
	void setOutput(String path) {
		super.addArg("--output", path);
	}
	
	void setRunAsPaired() {
		super.addArg("--run_as_paired");
	}
	
	public static void runSingle(String seqType, String single, int cpu, String out, boolean paired) {
		TrinityWrapper tr = new TrinityWrapper();
		tr.setSeqType(seqType);
		tr.setSingle(single);
		tr.setCPU(cpu);
		tr.setOutput(out);
		if(paired) tr.setRunAsPaired();
		tr.exec();
	}
	
	public static void runDual(String seqType, String left, String right, int cpu, String out) {
		TrinityWrapper tr = new TrinityWrapper();
		tr.setSeqType(seqType);
		tr.setLeft(left);
		tr.setRight(right);
		tr.setCPU(cpu);
		tr.setOutput(out);
		tr.exec();
	}
	
	// solve dependency
	public static boolean solve() {
		String cmd = PathConfig.TrinityPath + " 2>&1";
		String[] raw = Shell.exec(cmd);
		if(raw[0].contains("not found")) return false;
		for(int error_loc = 0; !raw[error_loc].contains("Required"); error_loc++) {
			if(error_loc + 1 == raw.length) return false; 
		}
		return true;
	}
}