package envs.config;

import pipeline.ExceptionHandler;
import pipeline.UFCGMainPipeline;
import envs.toolkit.ANSIHandler;
import envs.toolkit.FileStream;
import envs.toolkit.Prompt;
import envs.toolkit.Shell;

import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class GenericConfig {
	public static final boolean TEST = false; // Testing?
	/* Running project status */
	public static String PHEAD = ""; 		// Prompt header
	public static int HLEN = 0;				// Prompt maximum header length
	private static boolean custom_hlen = false;
	public static int MODULE = 0; // module
	
	public static void setHeader(String header) {
		PHEAD = header;
		if(!custom_hlen) HLEN  = header.length();
	}
	public static void setHeaderLength(int len) {
		HLEN  = len;
		custom_hlen = true;
	}
	
	public static void setModule(int module) {
		MODULE = module;
	}
	public static String getModuleName() {
		switch(MODULE) {
		case UFCGMainPipeline.MODULE_PROFILE: return "profile";
		case UFCGMainPipeline.MODULE_PROFILE_RNA: return "profile-rna";
		case UFCGMainPipeline.MODULE_TREE: return "tree";
		case UFCGMainPipeline.MODULE_PRUNE: return "prune";
		default: return "";
		}
	}
	
	public static final String SESSION_UID = Long.toHexString(new Random().nextLong());
	public static String TEMP_HEADER = SESSION_UID + "_";
	
	public static boolean DEV = false;      // Developer mode
	public static boolean VERB = false; 	// Program verbosity
	public static boolean NOCOLOR = false;  // No color escapes
	public static boolean TSTAMP = true;   // Print timestamp
	public static boolean INTRON = true;   // Include intron
	public static boolean INTERACT = false; // Interactive mode
	public static boolean FORCE = false;    // Force to delete existing file
	
	/* Runtime custom variables */
	public static String FILENAME = "";
	public static void setFilename(String name) {
		FILENAME = name;
	}
	public static String LABEL = null;
	public static void setLabel(String lab) {
		LABEL = lab;
	}
	public static String ACCESS = "";
	public static void setAccession(String acc) {
		ACCESS = acc;
	}
	public static String TAXON = null;
	public static void setTaxon(String tax) {
		TAXON = tax;
	}
	public static String NCBI = null;
	public static void setNcbi(String name) {
		NCBI = name;
	}
	public static String STRAIN = null;
	public static void setStrain(String str) {
		STRAIN = str;
	}
	public static String TAXONOMY = null;
	public static void setTaxonomy(String txn) {
		TAXONOMY = txn;
	}
	public static void setSystem(String filename, String accession, String label, String taxon, String ncbi, String strain, String taxonomy) {
		setFilename(filename);
		setAccession(accession);
		setLabel(label);
		setTaxon(taxon);
		setNcbi(ncbi);
		setStrain(strain);
		setTaxonomy(taxonomy);
	}
	
	public static int ThreadPoolSize = 1;
	public static void setThreadPoolSize(int size) {
		ThreadPoolSize = size;
	}
	public static int setThreadPoolSize(String val) {
		try {
			Prompt.talk("Custom CPU thread count check : " + ANSIHandler.wrapper(val, 'B'));
			int size = Integer.parseInt(val);
			
			if(size < 1) {
				ExceptionHandler.pass(size);
				ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			}
			
			setThreadPoolSize(size);
			return 0;
		}
		catch(NumberFormatException nfe) {
			ExceptionHandler.pass(val + " (Integer value expected)");
			ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			return 1;
		}
	}
	
	public static double FastBlockSearchCutoff = 0.5;
	public static void setFastBlockSearchCutoff(double cutoff) {
		FastBlockSearchCutoff = cutoff;
	}
	public static int setFastBlockSearchCutoff(String val) {
		try {
			Prompt.talk("Custom fastBlockSearch cutoff check : " + ANSIHandler.wrapper(val, 'B'));
			double cutoff = Double.parseDouble(val);
			
			if(cutoff <= .0) {
				ExceptionHandler.pass(cutoff);
				ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			}
			
			setFastBlockSearchCutoff(cutoff);
			return 0;
		}
		catch(NumberFormatException nfe) {
			ExceptionHandler.pass(val + " (Floating point value expected)");
			ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			return 1;
		}
	}
	
	public static int FastBlockSearchHits = 5;
	public static void setFastBlockSearchHits(int hits) {
		FastBlockSearchHits = hits;
	}
	public static int setFastBlockSearchHits(String val) {
		try {
			Prompt.talk("Custom fastBlockSearch hits check : " + ANSIHandler.wrapper(val, 'B'));
			int hits = Integer.parseInt(val);
			
			if(hits <= 0) {
				ExceptionHandler.pass(hits);
				ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			}
			
			setFastBlockSearchHits(hits);
			return 0;
		}
		catch(NumberFormatException nfe) {
			ExceptionHandler.pass(val + " (Integer value expected)");
			ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			return 1;
		}
	}
	
	public static int AugustusPredictionOffset = 10000;
	public static void setAugustusPredictionOffset(int offset) {
		AugustusPredictionOffset = offset;
	}
	public static int setAugustusPredictionOffset(String val) {
		try {
			Prompt.talk("Custom AUGUSTUS offset window check : " + ANSIHandler.wrapper(val, 'B'));
			int offset = Integer.parseInt(val);
			
			if(offset < 1) {
				ExceptionHandler.pass(offset);
				ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			}
			
			GenericConfig.setAugustusPredictionOffset(offset);
			return 0;
		}
		catch(NumberFormatException nfe) {
			ExceptionHandler.pass(val + " (Integer value expected)");
			ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			return 1;
		}
	}
	
	public static double HmmsearchScoreCutoff = 100.0;
	public static void setHmmsearchScoreCutoff(double cutoff) {
		HmmsearchScoreCutoff = cutoff;
	}
	public static int setHmmsearchScoreCutoff(String val) {
		try {
			Prompt.talk("Custom hmmsearch score cutoff check : " + ANSIHandler.wrapper(val, 'B'));
			double cutoff = Double.parseDouble(val);
			
			if(cutoff <= .0) {
				ExceptionHandler.pass(cutoff);
				ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			}
			
			setHmmsearchScoreCutoff(cutoff);
			return 0;
		}
		catch(NumberFormatException nfe) {
			ExceptionHandler.pass(val + " (Floating point value expected)");
			ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			return 1;
		}
	}
	
	public static double EvalueCutoff = 1e-30;
	public static void setEvalueCutoff(double cutoff) {
		EvalueCutoff = cutoff;
	}
	public static int setEvalueCutoff(String val) {
		try {
			Prompt.talk("Custom e-value cutoff check : " + ANSIHandler.wrapper(val, 'B'));
			double cutoff = Double.parseDouble(val);
			
			if(cutoff <= .0) {
				ExceptionHandler.pass(cutoff);
				ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			}
			
			setHmmsearchScoreCutoff(cutoff);
			return 0;
		}
		catch(NumberFormatException nfe) {
			ExceptionHandler.pass(val + " (Floating point value expected)");
			ExceptionHandler.handle(ExceptionHandler.INVALID_VALUE);
			return 1;
		}
	}
	
	// Reference Fungal Core Gene
/*	public static final String[] FCG_ALT = {
			"ACC1",   "ALA1",   "ASN1",   "ASP1",   "BMS1",   "BUD7",   "CDC48",  "CHS2",   "CYR1",
			"FZO1",   "GEA2",   "GLT1",   "HIS4",   "HMG1",   "HSP60",  "HSP70",  "HSP104", "INO80",
			"IQG1",   "KAP95",  "KOG1",   "MCM2",   "MCM5",   "MCM7",   "MSH2",   "MSH6",   "MYO2",
			"NOT1",   "PAB1",   "PEX6",   "PIM1",   "PKC1",   "PYR1",   "RAD50",  "RPA1",   "RPB1",
			"RPB2",   "RPN1",   "SEC7",   "SEC18",  "SEC24",  "SIN3",   "SKI2",   "SLA2",   "SMC2",
			"SMC3",   "SPB1",   "SPT6",   "SPT16",  "STT4",   "TAF1",   "TEF1",   "TOR1",   "TUB2",
			"UBA1",   "UTP20",  "VPS13",  "XPO1"
	};*/
	public static final String[] FCG_REF = {
			"ACO1",   "ACO2",   "ACT1",   "ATP6",   "CCT2",   "CCT4",   "CCT7",   "CCT8",   "CDC21",  "CDC48",
			"CMD1",   "COB",    "COX1",   "COX2",   "COX3",   "CPA2",   "DBP1",   "DBP2",   "ECM10",  "EFT1",
			"FAL1",   "FKS1",   "HRR25",  "HSP60",  "ILV2",   "IMD2",   "KAR2",   "KGD1",   "LEU1",   "MCM2",
			"MCM7",   "MDH1",   "MTR4",   "NBP35",  "NDI1",   "OLI1",   "PAH1",   "PGK1",   "PHO85",  "PRP43",
			"RET1",   "RPB2",   "RPL2B",  "RPL3",   "RPL4A",  "RPL8A",  "RPO21",  "RPP0",   "RPS0A",  "RPT1",
			"RPT2",   "RPT3",   "RPT5",   "RPT6",   "SAM1",   "SAM2",   "SSA1",   "SSA3",   "SSB1",   "SSC1",
			"SUP45",  "TCP1",   "TEF1",   "TIF1",   "TOP1",   "TRP3",   "TSR1",   "TUB1",   "TUB2",   "UBI4",
			"URA2"
	};
	// In order considering the calculation time of each gene
	public static final String[] FCG_ORD = {
			"URA2","KAR2","FKS1","SSB1","ACO2","SSA3","CPA2","UBI4","ACO1","MCM7",
			"PRP43","RPO21","RPB2","SSA1","MTR4","RET1","KGD1","RPT5","RPT6","MCM2",
			"EFT1","LEU1","ECM10","ILV2","FAL1","TRP3","RPT2","CCT4","PHO85","DBP1",
			"SSC1","CDC48","MDH1","HRR25","RPT3","TIF1","CCT2","TUB2","IMD2","TCP1",
			"HSP60","CCT7","SAM1","RPL4A","RPT1","SAM2","TEF1","RPL8A","TUB1","SUP45",
			"ACT1","DBP2","NBP35","RPL3","RPP0","RPS0A","CDC21","RPL2B",
			"ATP6","CCT8","CMD1","COB","COX2","COX3","NDI1","OLI1","PAH1","PGK1","TOP1","TSR1",
	};
	public static final String[] FCG_COG = {
			"C","C","Z","O","O","O","F","M/D/T","E/F","L",
			"L","O","J","L","M","T","O","E/H","T","O",
			"C","E","L","L","C","L","D","T","J","K",
			"K","J","J","J","J","K","J","J","O","O",
			"O","O","O","H","H","O","O","O","O","J",
			"O","J","L","E","Z","Z","O","E/F"
	};
	public static final String[] FCG_DSC = {
			"Aconitate hydratase",
			"Aconitate hydratase",
			"Actin",
			"F1F0 ATP synthase subunit",
			"Chaperonin-containing T-complex subunit",
			"Chaperonin-containing T-complex subunit",
			"Chaperonin-containing T-complex subunit",
			"Chaperonin-containing T-complex subunit",
			"Thymidylate synthase",
			"AAA family ATPase",
			
			"Calmodulin",
			"Cytochrome b",
			"Cytochrome c oxidase subunit",
			"Cytochrome c oxidase subunit",
			"Cytochrome c oxidase subunit",
			"Carbamoyl-phosphate synthase",
			"DEAD-box ATP-dependent RNA helicase",
			"DEAD-box ATP-dependent RNA helicase",
			"Hsp70 family ATPase",
			"Elongation factor 2",
			
			"ATP-dependent RNA helicase",
			"1,3-beta-D-glucan synthase",
			"Serine/threonine protein kinase",
			"Chaperone ATPase",
			"Acetolactate synthase catalytic subunit",
			"IMP dehydrogenase",
			"Hsp70 family ATPase",
			"Alpha-ketoglutarate dehydrogenase",
			"3-isopropylmalate dehydratase",
			"MCM DNA helicase complex subunit",

			"Mini-chromosome maintenance complex subunit",
			"Malate dehydrogenase",
			"ATP-dependent RNA helicase",
			"Fe-S cluster-binding ATPase",
			"NADH-ubiquinone reductase",
			"F0 ATP synthase subunit",
			"Phosphatidate phosphatase",
			"Phosphoglycerate kinase",
			"Cyclin-dependent serine/threonine-protein kinase",
			"DEAH-box ATP-dependent RNA helicase",

			"DNA-directed RNA polymerase III core subunit",
			"DNA-directed RNA polymerase II core subunit",
			"Ribosomal 60S subunit",
			"Ribosomal 60S subunit",
			"Ribosomal 60S subunit",
			"Ribosomal 60S subunit",
			"DNA-directed RNA polymerase II core subunit",
			"Ribosomal protein P0",
			"Ribosomal 40S subunit",
			"Proteasome regulatory particle base subunit",

			"Proteasome regulatory particle base subunit",
			"Proteasome regulatory particle base subunit",
			"Proteasome regulatory particle base subunit",
			"Proteasome regulatory particle base subunit",
			"Methionine adenosyltransferase",
			"Methionine adenosyltransferase",
			"Hsp70 family ATPase",
			"Hsp70 family ATPase",
			"Hsp70 family ATPase",
			"Hsp70 family ATPase",

			"Translation termination factor eRF1",
			"Chaperonin-containing T-complex alpha subunit",
			"Translation elongation factor EF-1 alpha",
			"Translation initiation factor eIF4A",
			"DNA topoisomerase 1",
			"Bifunctional anthranilate synthase/indole-3-glycerol-phosphate synthase",
			"Ribosome maturation factor",
			"Alpha-tubulin",
			"Beta-tubulin",
			"Ubiquitin",

			"Bifunctional carbamoylphosphate synthetase/aspartate transcarbamylase"
	};
	
	// Tree models
	public static final String[] PROTEIN_RAXML_MODELS = { 
		"PROTCATDAYHOFF", "PROTCATDCMUT", "PROTCATJTT", "PROTCATMTREV", "PROTCATWAG",
		"PROTCATRTREV", "PROTCATCPREV", "PROTCATVT", "PROTCATBLOSUM62", "PROTCATMTMAM", "PROTCATLG",
		"PROTCATMTART", "PROTCATMTZOA", "PROTCATPMB", "PROTCATHIVB", "PROTCATHIVW",
		"PROTCATJTTDCMUT", "PROTCATFLU", "PROTCATSTMTREV", "PROTCATDUMMY", "PROTCATDUMMY2",
		"PROTCATAUTO", "PROTCATLG4M", "PROTCATLG4X", "PROTCATPROT_FILE", "PROTCATGTR_UNLINKED",
		"PROTCATGTR", "ASC_PROTCATDAYHOFF", "ASC_PROTCATDCMUT", "ASC_PROTCATJTT",
		"ASC_PROTCATMTREV", "ASC_PROTCATWAG", "ASC_PROTCATRTREV", "ASC_PROTCATCPREV",
		"ASC_PROTCATVT", "ASC_PROTCATBLOSUM62", "ASC_PROTCATMTMAM", "ASC_PROTCATLG",
		"ASC_PROTCATMTART", "ASC_PROTCATMTZOA", "ASC_PROTCATPMB", "ASC_PROTCATHIVB",
		"ASC_PROTCATHIVW", "ASC_PROTCATJTTDCMUT", "ASC_PROTCATFLU", "ASC_PROTCATSTMTREV",
		"ASC_PROTCATDUMMY", "ASC_PROTCATDUMMY2", "ASC_PROTCATAUTO", "ASC_PROTCATLG4M",
		"ASC_PROTCATLG4X", "ASC_PROTCATPROT_FILE", "ASC_PROTCATGTR_UNLINKED", "ASC_PROTCATGTR",
		"PROTCATIDAYHOFF", "PROTCATIDCMUT", "PROTCATIJTT", "PROTCATIMTREV", "PROTCATIWAG",
		"PROTCATIRTREV", "PROTCATICPREV", "PROTCATIVT", "PROTCATIBLOSUM62", "PROTCATIMTMAM",
		"PROTCATILG", "PROTCATIMTART", "PROTCATIMTZOA", "PROTCATIPMB", "PROTCATIHIVB",
		"PROTCATIHIVW", "PROTCATIJTTDCMUT", "PROTCATIFLU", "PROTCATISTMTREV", "PROTCATIDUMMY",
		"PROTCATIDUMMY2", "PROTCATIAUTO", "PROTCATILG4M", "PROTCATILG4X", "PROTCATIPROT_FILE",
		"PROTCATIGTR_UNLINKED", "PROTCATIGTR", "PROTGAMMADAYHOFF", "PROTGAMMADCMUT", "PROTGAMMAJTT",
		"PROTGAMMAMTREV", "PROTGAMMAWAG", "PROTGAMMARTREV", "PROTGAMMACPREV", "PROTGAMMAVT",
		"PROTGAMMABLOSUM62", "PROTGAMMAMTMAM", "PROTGAMMALG", "PROTGAMMAMTART", "PROTGAMMAMTZOA",
		"PROTGAMMAPMB", "PROTGAMMAHIVB", "PROTGAMMAHIVW", "PROTGAMMAJTTDCMUT", "PROTGAMMAFLU",
		"PROTGAMMASTMTREV", "PROTGAMMADUMMY", "PROTGAMMADUMMY2", "PROTGAMMAAUTO", "PROTGAMMALG4M",
		"PROTGAMMALG4X", "PROTGAMMAPROT_FILE", "PROTGAMMAGTR_UNLINKED", "PROTGAMMAGTR",
		"ASC_PROTGAMMADAYHOFF", "ASC_PROTGAMMADCMUT", "ASC_PROTGAMMAJTT", "ASC_PROTGAMMAMTREV",
		"ASC_PROTGAMMAWAG", "ASC_PROTGAMMARTREV", "ASC_PROTGAMMACPREV", "ASC_PROTGAMMAVT",
		"ASC_PROTGAMMABLOSUM62", "ASC_PROTGAMMAMTMAM", "ASC_PROTGAMMALG", "ASC_PROTGAMMAMTART",
		"ASC_PROTGAMMAMTZOA", "ASC_PROTGAMMAPMB", "ASC_PROTGAMMAHIVB", "ASC_PROTGAMMAHIVW",
		"ASC_PROTGAMMAJTTDCMUT", "ASC_PROTGAMMAFLU", "ASC_PROTGAMMASTMTREV", "ASC_PROTGAMMADUMMY",
		"ASC_PROTGAMMADUMMY2", "ASC_PROTGAMMAAUTO", "ASC_PROTGAMMALG4M", "ASC_PROTGAMMALG4X",
		"ASC_PROTGAMMAPROT_FILE", "ASC_PROTGAMMAGTR_UNLINKED", "ASC_PROTGAMMAGTR",
		"PROTGAMMAIDAYHOFF", "PROTGAMMAIDCMUT", "PROTGAMMAIJTT", "PROTGAMMAIMTREV", "PROTGAMMAIWAG",
		"PROTGAMMAIRTREV", "PROTGAMMAICPREV", "PROTGAMMAIVT", "PROTGAMMAIBLOSUM62",
		"PROTGAMMAIMTMAM", "PROTGAMMAILG", "PROTGAMMAIMTART", "PROTGAMMAIMTZOA", "PROTGAMMAIPMB",
		"PROTGAMMAIHIVB", "PROTGAMMAIHIVW", "PROTGAMMAIJTTDCMUT", "PROTGAMMAIFLU",
		"PROTGAMMAISTMTREV", "PROTGAMMAIDUMMY", "PROTGAMMAIDUMMY2", "PROTGAMMAIAUTO",
		"PROTGAMMAILG4M", "PROTGAMMAILG4X", "PROTGAMMAIPROT_FILE", "PROTGAMMAIGTR_UNLINKED",
		"PROTGAMMAIGTR", "PROTCATDAYHOFFF", "PROTCATDCMUTF", "PROTCATJTTF", "PROTCATMTREVF",
		"PROTCATWAGF", "PROTCATRTREVF", "PROTCATCPREVF", "PROTCATVTF", "PROTCATBLOSUM62F",
		"PROTCATMTMAMF", "PROTCATLGF", "PROTCATMTARTF", "PROTCATMTZOAF", "PROTCATPMBF",
		"PROTCATHIVBF", "PROTCATHIVWF", "PROTCATJTTDCMUTF", "PROTCATFLUF", "PROTCATSTMTREVF",
		"PROTCATDUMMYF", "PROTCATDUMMY2F", "PROTCATAUTOF", "PROTCATLG4MF", "PROTCATLG4XF",
		"PROTCATPROT_FILEF", "PROTCATGTR_UNLINKEDF", "PROTCATGTRF", "ASC_PROTCATDAYHOFFF",
		"ASC_PROTCATDCMUTF", "ASC_PROTCATJTTF", "ASC_PROTCATMTREVF", "ASC_PROTCATWAGF",
		"ASC_PROTCATRTREVF", "ASC_PROTCATCPREVF", "ASC_PROTCATVTF", "ASC_PROTCATBLOSUM62F",
		"ASC_PROTCATMTMAMF", "ASC_PROTCATLGF", "ASC_PROTCATMTARTF", "ASC_PROTCATMTZOAF",
		"ASC_PROTCATPMBF", "ASC_PROTCATHIVBF", "ASC_PROTCATHIVWF", "ASC_PROTCATJTTDCMUTF",
		"ASC_PROTCATFLUF", "ASC_PROTCATSTMTREVF", "ASC_PROTCATDUMMYF", "ASC_PROTCATDUMMY2F",
		"ASC_PROTCATAUTOF", "ASC_PROTCATLG4MF", "ASC_PROTCATLG4XF", "ASC_PROTCATPROT_FILEF",
		"ASC_PROTCATGTR_UNLINKEDF", "ASC_PROTCATGTRF", "PROTCATIDAYHOFFF", "PROTCATIDCMUTF",
		"PROTCATIJTTF", "PROTCATIMTREVF", "PROTCATIWAGF", "PROTCATIRTREVF", "PROTCATICPREVF",
		"PROTCATIVTF", "PROTCATIBLOSUM62F", "PROTCATIMTMAMF", "PROTCATILGF", "PROTCATIMTARTF",
		"PROTCATIMTZOAF", "PROTCATIPMBF", "PROTCATIHIVBF", "PROTCATIHIVWF", "PROTCATIJTTDCMUTF",
		"PROTCATIFLUF", "PROTCATISTMTREVF", "PROTCATIDUMMYF", "PROTCATIDUMMY2F", "PROTCATIAUTOF",
		"PROTCATILG4MF", "PROTCATILG4XF", "PROTCATIPROT_FILEF", "PROTCATIGTR_UNLINKEDF",
		"PROTCATIGTRF", "PROTGAMMADAYHOFFF", "PROTGAMMADCMUTF", "PROTGAMMAJTTF", "PROTGAMMAMTREVF",
		"PROTGAMMAWAGF", "PROTGAMMARTREVF", "PROTGAMMACPREVF", "PROTGAMMAVTF", "PROTGAMMABLOSUM62F",
		"PROTGAMMAMTMAMF", "PROTGAMMALGF", "PROTGAMMAMTARTF", "PROTGAMMAMTZOAF", "PROTGAMMAPMBF",
		"PROTGAMMAHIVBF", "PROTGAMMAHIVWF", "PROTGAMMAJTTDCMUTF", "PROTGAMMAFLUF",
		"PROTGAMMASTMTREVF", "PROTGAMMADUMMYF", "PROTGAMMADUMMY2F", "PROTGAMMAAUTOF",
		"PROTGAMMALG4MF", "PROTGAMMALG4XF", "PROTGAMMAPROT_FILEF", "PROTGAMMAGTR_UNLINKEDF",
		"PROTGAMMAGTRF", "ASC_PROTGAMMADAYHOFFF", "ASC_PROTGAMMADCMUTF", "ASC_PROTGAMMAJTTF",
		"ASC_PROTGAMMAMTREVF", "ASC_PROTGAMMAWAGF", "ASC_PROTGAMMARTREVF", "ASC_PROTGAMMACPREVF",
		"ASC_PROTGAMMAVTF", "ASC_PROTGAMMABLOSUM62F", "ASC_PROTGAMMAMTMAMF", "ASC_PROTGAMMALGF",
		"ASC_PROTGAMMAMTARTF", "ASC_PROTGAMMAMTZOAF", "ASC_PROTGAMMAPMBF", "ASC_PROTGAMMAHIVBF",
		"ASC_PROTGAMMAHIVWF", "ASC_PROTGAMMAJTTDCMUTF", "ASC_PROTGAMMAFLUF",
		"ASC_PROTGAMMASTMTREVF", "ASC_PROTGAMMADUMMYF", "ASC_PROTGAMMADUMMY2F",
		"ASC_PROTGAMMAAUTOF", "ASC_PROTGAMMALG4MF", "ASC_PROTGAMMALG4XF", "ASC_PROTGAMMAPROT_FILEF",
		"ASC_PROTGAMMAGTR_UNLINKEDF", "ASC_PROTGAMMAGTRF", "PROTGAMMAIDAYHOFFF", "PROTGAMMAIDCMUTF",
		"PROTGAMMAIJTTF", "PROTGAMMAIMTREVF", "PROTGAMMAIWAGF", "PROTGAMMAIRTREVF",
		"PROTGAMMAICPREVF", "PROTGAMMAIVTF", "PROTGAMMAIBLOSUM62F", "PROTGAMMAIMTMAMF",
		"PROTGAMMAILGF", "PROTGAMMAIMTARTF", "PROTGAMMAIMTZOAF", "PROTGAMMAIPMBF",
		"PROTGAMMAIHIVBF", "PROTGAMMAIHIVWF", "PROTGAMMAIJTTDCMUTF", "PROTGAMMAIFLUF",
		"PROTGAMMAISTMTREVF", "PROTGAMMAIDUMMYF", "PROTGAMMAIDUMMY2F", "PROTGAMMAIAUTOF",
		"PROTGAMMAILG4MF", "PROTGAMMAILG4XF", "PROTGAMMAIPROT_FILEF", "PROTGAMMAIGTR_UNLINKEDF",
		"PROTGAMMAIGTRF", "PROTCATDAYHOFFX", "PROTCATDCMUTX", "PROTCATJTTX", "PROTCATMTREVX",
		"PROTCATWAGX", "PROTCATRTREVX", "PROTCATCPREVX", "PROTCATVTX", "PROTCATBLOSUM62X",
		"PROTCATMTMAMX", "PROTCATLGX", "PROTCATMTARTX", "PROTCATMTZOAX", "PROTCATPMBX",
		"PROTCATHIVBX", "PROTCATHIVWX", "PROTCATJTTDCMUTX", "PROTCATFLUX", "PROTCATSTMTREVX",
		"PROTCATDUMMYX", "PROTCATDUMMY2X", "PROTCATAUTOX", "PROTCATLG4MX", "PROTCATLG4XX",
		"PROTCATPROT_FILEX", "PROTCATGTR_UNLINKEDX", "PROTCATGTRX", "ASC_PROTCATDAYHOFFX",
		"ASC_PROTCATDCMUTX", "ASC_PROTCATJTTX", "ASC_PROTCATMTREVX", "ASC_PROTCATWAGX",
		"ASC_PROTCATRTREVX", "ASC_PROTCATCPREVX", "ASC_PROTCATVTX", "ASC_PROTCATBLOSUM62X",
		"ASC_PROTCATMTMAMX", "ASC_PROTCATLGX", "ASC_PROTCATMTARTX", "ASC_PROTCATMTZOAX",
		"ASC_PROTCATPMBX", "ASC_PROTCATHIVBX", "ASC_PROTCATHIVWX", "ASC_PROTCATJTTDCMUTX",
		"ASC_PROTCATFLUX", "ASC_PROTCATSTMTREVX", "ASC_PROTCATDUMMYX", "ASC_PROTCATDUMMY2X",
		"ASC_PROTCATAUTOX", "ASC_PROTCATLG4MX", "ASC_PROTCATLG4XX", "ASC_PROTCATPROT_FILEX",
		"ASC_PROTCATGTR_UNLINKEDX", "ASC_PROTCATGTRX", "PROTCATIDAYHOFFX", "PROTCATIDCMUTX",
		"PROTCATIJTTX", "PROTCATIMTREVX", "PROTCATIWAGX", "PROTCATIRTREVX", "PROTCATICPREVX",
		"PROTCATIVTX", "PROTCATIBLOSUM62X", "PROTCATIMTMAMX", "PROTCATILGX", "PROTCATIMTARTX",
		"PROTCATIMTZOAX", "PROTCATIPMBX", "PROTCATIHIVBX", "PROTCATIHIVWX", "PROTCATIJTTDCMUTX",
		"PROTCATIFLUX", "PROTCATISTMTREVX", "PROTCATIDUMMYX", "PROTCATIDUMMY2X", "PROTCATIAUTOX",
		"PROTCATILG4MX", "PROTCATILG4XX", "PROTCATIPROT_FILEX", "PROTCATIGTR_UNLINKEDX",
		"PROTCATIGTRX", "PROTGAMMADAYHOFFX", "PROTGAMMADCMUTX", "PROTGAMMAJTTX", "PROTGAMMAMTREVX",
		"PROTGAMMAWAGX", "PROTGAMMARTREVX", "PROTGAMMACPREVX", "PROTGAMMAVTX", "PROTGAMMABLOSUM62X",
		"PROTGAMMAMTMAMX", "PROTGAMMALGX", "PROTGAMMAMTARTX", "PROTGAMMAMTZOAX", "PROTGAMMAPMBX",
		"PROTGAMMAHIVBX", "PROTGAMMAHIVWX", "PROTGAMMAJTTDCMUTX", "PROTGAMMAFLUX",
		"PROTGAMMASTMTREVX", "PROTGAMMADUMMYX", "PROTGAMMADUMMY2X", "PROTGAMMAAUTOX",
		"PROTGAMMALG4MX", "PROTGAMMALG4XX", "PROTGAMMAPROT_FILEX", "PROTGAMMAGTR_UNLINKEDX",
		"PROTGAMMAGTRX", "ASC_PROTGAMMADAYHOFFX", "ASC_PROTGAMMADCMUTX", "ASC_PROTGAMMAJTTX",
		"ASC_PROTGAMMAMTREVX", "ASC_PROTGAMMAWAGX", "ASC_PROTGAMMARTREVX", "ASC_PROTGAMMACPREVX",
		"ASC_PROTGAMMAVTX", "ASC_PROTGAMMABLOSUM62X", "ASC_PROTGAMMAMTMAMX", "ASC_PROTGAMMALGX",
		"ASC_PROTGAMMAMTARTX", "ASC_PROTGAMMAMTZOAX", "ASC_PROTGAMMAPMBX", "ASC_PROTGAMMAHIVBX",
		"ASC_PROTGAMMAHIVWX", "ASC_PROTGAMMAJTTDCMUTX", "ASC_PROTGAMMAFLUX",
		"ASC_PROTGAMMASTMTREVX", "ASC_PROTGAMMADUMMYX", "ASC_PROTGAMMADUMMY2X",
		"ASC_PROTGAMMAAUTOX", "ASC_PROTGAMMALG4MX", "ASC_PROTGAMMALG4XX", "ASC_PROTGAMMAPROT_FILEX",
		"ASC_PROTGAMMAGTR_UNLINKEDX", "ASC_PROTGAMMAGTRX", "PROTGAMMAIDAYHOFFX", "PROTGAMMAIDCMUTX",
		"PROTGAMMAIJTTX", "PROTGAMMAIMTREVX", "PROTGAMMAIWAGX", "PROTGAMMAIRTREVX",
		"PROTGAMMAICPREVX", "PROTGAMMAIVTX", "PROTGAMMAIBLOSUM62X", "PROTGAMMAIMTMAMX",
		"PROTGAMMAILGX", "PROTGAMMAIMTARTX", "PROTGAMMAIMTZOAX", "PROTGAMMAIPMBX",
		"PROTGAMMAIHIVBX", "PROTGAMMAIHIVWX", "PROTGAMMAIJTTDCMUTX", "PROTGAMMAIFLUX",
		"PROTGAMMAISTMTREVX", "PROTGAMMAIDUMMYX", "PROTGAMMAIDUMMY2X", "PROTGAMMAIAUTOX",
		"PROTGAMMAILG4MX", "PROTGAMMAILG4XX", "PROTGAMMAIPROT_FILEX", "PROTGAMMAIGTR_UNLINKEDX",
		"PROTGAMMAIGTRX"
	},
	PROTEIN_FASTTREE_MODELS = {
		"JTTcat", "LGcat", "WAGcat", "JTTgamma", "LGgamma", "WAGgamma"
	},
	PROTEIN_IQTREE_MODELS = {
			
	},
	NUCLEOTIDE_RAXML_MODELS = {
		"GTRCAT", "GTRCATI", "ASC_GTRCAT", "GTRGAMMA", "ASC_GTRGAMMA", "GTRGAMMAI",
		"GTRCATX", "GTRCATIX", "ASC_GTRCATX", "GTRGAMMAX", "ASC_GTRGAMMAX", "GTRGAMMAIX"
	},
	NUCLEOTIDE_FASTTREE_MODELS = {
		"JCcat", "GTRcat", "JCgamma", "GTRgamma"
	},
	NUCLEOTIDE_IQTREE_MODELS = {
			
	};
			
	/* Gene set definition */
	public static String GENESET = "PRO";
	public static String[] FCG = FCG_REF; // core genes for this process
	public static void setGeneset(String geneset) {GENESET = geneset;}
	
	public static final int TARGET_NUC = 0x01,
							TARGET_PRO = 0x02,
							TARGET_BUSCO = 0x03;
	
	public static boolean NUC = false, PRO = false, BUSCO = false;
	public static int TARGET = 0;
	public static int solveGeneset() {
		List<String> pros = new ArrayList<String>(); // custom protein marker set
		for(String ele : GENESET.split(",")) {
			if(ele.equals("NUC")) NUC = true;
			else if(ele.equals("PRO")) PRO = true;
			else if(ele.equals("BUSCO")) BUSCO = true;
			// else if(!Arrays.asList(FCG_REF).contains(ele)) return 1; // not allowing non-core protein markers
			else {
				PRO = true;
				pros.add(ele);
			}
		}
		
		if(!(NUC | PRO | BUSCO)) return 1; // invalid if nothing is detected
		if(pros.size() > 0) FCG = Arrays.copyOf(pros.toArray(), pros.toArray().length, String[].class); // use custom proteins if detected
		return 0;
	}
	
	public static String[] QUERY_GENES = null; // query genes
	public static void setQueryGenes(String[] q, int target) {QUERY_GENES = q; TARGET = target;}
	
	public static String[] BUSCOS = null;
	public static int getBuscos() {
		List<String> bids = new ArrayList<String>();
		
		// get list from sequence directory
		String cmd = "ls -1 " + PathConfig.SeqPath + "busco > " + PathConfig.TempPath + GenericConfig.TEMP_HEADER + "busco.seq.list";
		Shell.exec(cmd);
		try {
			FileStream tmpListStream = new FileStream(PathConfig.TempPath + GenericConfig.TEMP_HEADER + "busco.seq.list", 'r');
			tmpListStream.isTemp();
			String buf;
			while((buf = tmpListStream.readLine()) != null) {
				if(!buf.endsWith(".fa")) continue;
				bids.add(buf.substring(0, buf.indexOf('.')));
			}
			
			tmpListStream.wipe(true);
			BUSCOS = Arrays.copyOf(bids.toArray(), bids.toArray().length, String[].class);
		}
		catch(java.io.IOException e) {
			e.printStackTrace();
			ExceptionHandler.handle(ExceptionHandler.EXCEPTION);
		}
		
		// validate list using model directory
		cmd = "ls -1 " + PathConfig.ModelPath + "busco > " + PathConfig.TempPath + GenericConfig.TEMP_HEADER + "busco.model.list";
		Shell.exec(cmd);
		try {
			int[] cnt = new int[BUSCOS.length];
			for(int i = 0; i < cnt.length; i++) cnt[i] = -1;
			
			FileStream tmpListStream = new FileStream(PathConfig.TempPath + GenericConfig.TEMP_HEADER + "busco.model.list", 'r');
			tmpListStream.isTemp();
			String buf;
			while((buf = tmpListStream.readLine()) != null) {
				if(!buf.endsWith(".hmm")) continue;
				int loc = 0;
				for(; loc < cnt.length; loc++) if(buf.contains(BUSCOS[loc])) break;
				if(loc == cnt.length) continue;
				cnt[loc]++;
			}
			tmpListStream.wipe(true);
			
			for(int c : cnt) if(c < 0) return 1;
		}
		catch(java.io.IOException e) {
			e.printStackTrace();
			ExceptionHandler.handle(ExceptionHandler.EXCEPTION);
		}
		
		Prompt.talk("Number of BUSCOs to extract : " + ANSIHandler.wrapper(String.valueOf(BUSCOS.length), 'B'));
		return 0;
	}
	
/*	public static int setCustomCoreList(String list) {
		Prompt.talk("Custom core gene list check : " + ANSIHandler.wrapper(list, 'B'));
		
		if(!list.contains(",")) {
			if(!Arrays.asList(FCG_REF).contains(list)) {
				ExceptionHandler.handle(ExceptionHandler.WRONG_CORE_FORMAT);
				return 1;
			}
		}
		
		String[] split = list.split(",");
//		for(String cg : split) {
//			if(!Arrays.asList(FCG_REF).contains(cg)) {
//				ExceptionHandler.pass(cg);
//				ExceptionHandler.handle(ExceptionHandler.INVALID_GENE_NAME);
//				return 1;
//			}
//		}
		
		FCG = split;
		if(VERB) Prompt.print(String.format("Custom gene set containing %d genes confirmed.", FCG.length));
		return 0;
	} */
	
	public static String geneString() {
		String gstr = "";
		for(String fcg : FCG_REF) gstr += fcg + ",";
		return gstr.substring(0, gstr.length() - 1);
	}
}
