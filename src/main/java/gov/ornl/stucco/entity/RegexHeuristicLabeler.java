package gov.ornl.stucco.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;
import gov.ornl.stucco.entity.CyberHeuristicAnnotator.CyberHeuristicAnnotation;
import gov.ornl.stucco.entity.models.RegexContext;
import gov.ornl.stucco.entity.models.RegexContext.LabelKey;
import gov.ornl.stucco.entity.models.RegexContext.WordKey;

public class RegexHeuristicLabeler {
	
	public static CoreLabel EMPTY_CORELABEL = new CoreLabel();
	
	public static Pattern pattern0 = Pattern.compile("[0-9x\\.\\-]");
	public static Pattern pattern1 = Pattern.compile("^(before|through) [0-9x\\.\\-]");
	public static Pattern pattern2 = Pattern.compile("[0-9x\\.][0-9x\\.\\-]* and earlier$");
	public static Pattern pattern3 = Pattern.compile("^([,]|and) (and )?[0-9]$");
	public static Pattern pattern4 = Pattern.compile("\\(\\)$");
	public static Pattern pattern5 = Pattern.compile("^[vV]ersion[_\\-a-zA-Z0-9]* [0-9]");
	public static Pattern pattern6 = Pattern.compile("^(all|every) (supported )?(versions?|releases?)$");
	public static Pattern pattern7 = Pattern.compile("^prior to$");
	public static Pattern pattern8 = Pattern.compile("^java ((runtime environment)|(web start)|(for business)|(system web (server)?)) [0-9.\\-_]+$", Pattern.CASE_INSENSITIVE);
	public static Pattern pattern9 = Pattern.compile("^java system access manager [0-9.\\-_]+$", Pattern.CASE_INSENSITIVE);
	public static Pattern pattern10 = Pattern.compile("^java (plug-in|se|ee|me) [0-9.\\-_]+$", Pattern.CASE_INSENSITIVE);
	public static Pattern pattern11 = Pattern.compile("[0-9]+");
	public static Pattern pattern12 = Pattern.compile("^j2se[0-9]*$", Pattern.CASE_INSENSITIVE);
	public static Pattern pattern13 = Pattern.compile("^pre[0-9a-zA-Z._-]* [0-9]");
	public static Pattern pattern14 = Pattern.compile("^(release|[uU]pdate)[_\\-a-zA-Z0-9]* [0-9]");
	public static Pattern pattern15 = Pattern.compile("JavaVM");
	public static Pattern pattern16 = Pattern.compile("\b[bB]eta|[aA]lpha\b");
	public static Pattern pattern17 = Pattern.compile("^service pack [0-9]$",Pattern.CASE_INSENSITIVE);
	public static Pattern pattern18 = Pattern.compile("^Java [A-Z]");
	public static Pattern pattern19 = Pattern.compile("CVE-[0-9]{4}-[0-9]{4}");
	public static Pattern pattern20 = Pattern.compile("MS[0-9]{2}-[0-9]{3}");
	public static Pattern pattern21 = Pattern.compile("^[0-9\\-._]+$");
	public static Pattern pattern22 = Pattern.compile("^(([a-zA-Z0-9\\_\\.]*[a-z0-9]+[A-Z]+)|([a-zA-Z0-9\\_\\.]*[A-Za-z0-9\\.]+\\_[a-zA-Z0-9\\.]+)) (and|or) (([a-zA-Z0-9\\_\\.]*[a-z0-9]+[A-Z]+)|([a-zA-Z0-9\\_\\.]*[A-Za-z0-9\\.]+\\_[a-zA-Z0-9\\.]+)) (function|parameter|method)");
	public static Pattern pattern23 = Pattern.compile("^function in \\.[a-zA-Z0-9]{1,4}$");
	public static Pattern pattern24 = Pattern.compile("^\\.[a-zA-Z0-9]{1,4} (files?|scripts?)$");
	public static Pattern pattern25 = Pattern.compile("^([a-zA-Z0-9.\\-_/]+\\.[a-zA-Z0-9]{1,4})$");
	public static Pattern pattern26 = Pattern.compile("\\.com|\\.org|\\.net|\\.mobi$");
	public static Pattern pattern27 = Pattern.compile("[a-z]\\.[A-Z]");
	public static Pattern pattern28 = Pattern.compile("^[A-Z]+ (component|plugin|plug-in)");
	public static Pattern pattern29 = Pattern.compile("^Oracle [A-Z]+");
	public static Pattern pattern30 = Pattern.compile("^WebKit$");
	public static Pattern pattern31 = Pattern.compile("^, and$");
	
	public static List<Pattern> patternList0 = new ArrayList<Pattern>() {{
		add(Pattern.compile("^[0-9]+(\\.|x)+[0-9a-zA-Z\\-\\.]{1,}$"));
		add(Pattern.compile("^[0-9.x]{2,}\\.+-[0-9a-zA-Z.]+$"));
		add(Pattern.compile("^[0-9\\.x]+\\.?[a-zA-Z.]+$"));
		add(Pattern.compile("^[0-9\\.x]+_[a-zA-Z0-9.]+$"));
		add(Pattern.compile("^[0-9\\.x]+\\%[0-9a-zA-Z.]+$"));
		add(Pattern.compile("^[0-9\\.x]+-([0-9.]+[a-zA-Z0-9.\\-_]*|[a-zA-Z0-9.\\-_]*[0-9.]+)$"));
		add(Pattern.compile("^[0-9a-z\\-_.]*\\%[0-9a-z\\-_.]+"));
		add(Pattern.compile("-[a-zA-Z0-9.]+$"));
		add(Pattern.compile("^alpha[_0-9a-zA-Z.]*"));
		add(Pattern.compile("^beta[_0-9a-zA-Z.]*"));
		add(Pattern.compile("^[A-Z]{1,3}[0-9]$"));
	}};
	
	public static Pattern no_label = Pattern.compile(CyberHeuristicAnnotator.O.toString());
	public static Pattern sw_product = Pattern.compile(CyberHeuristicAnnotator.SW_PRODUCT.toString());
	public static Pattern sw_vendor = Pattern.compile(CyberHeuristicAnnotator.SW_VENDOR.toString());
	public static Pattern sw_version = Pattern.compile(CyberHeuristicAnnotator.SW_VERSION.toString());
	public static Pattern sw_symbol = Pattern.compile(CyberHeuristicAnnotator.SW_SYMBOL.toString());
	public static Pattern vuln_ms = Pattern.compile(CyberHeuristicAnnotator.VULN_MS.toString());
	public static Pattern vuln_cve = Pattern.compile(CyberHeuristicAnnotator.VULN_CVE.toString());
	public static Pattern vuln_name = Pattern.compile(CyberHeuristicAnnotator.VULN_NAME.toString());
	public static Pattern vuln_desc = Pattern.compile(CyberHeuristicAnnotator.VULN_DESC.toString());
	
	private List<RegexContext> regexList;
	
	public RegexHeuristicLabeler() {
		regexList = new ArrayList<RegexContext>();
		init();
	}
	
	
	public void annotate(List<CoreLabel> tokenSublist) {
		for (RegexContext regex : regexList) {
			if (regex.evaluate(tokenSublist)) {
				break;
			}
		}
	}
	
	
	/**
	 * Set up all the RegexContext instances and add them to the list.
	 */
	private void init() {
		System.err.println("Loading regular expresions ...");
		// All these instances are ported from averaged_perceptron/comparison-code/preprocessing-code/regex_ad_hoc_tagger.py
		RegexContext regexContext = new RegexContext();
		// Lines 27-28
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addWordPatternList(WordKey.Word, patternList0);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 30-34
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.P_Label, CyberHeuristicAnnotator.SW_VERSION);
		List<WordKey> keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.P_Word);
		keyList.add(WordKey.Word);
		regexContext.addWordListPattern(keyList, pattern1);
		regexContext.addLabelRegex(LabelKey.P2_Label, sw_version);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 34-37
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.P_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.P_Word);
		keyList.add(WordKey.Word);
		regexContext.addWordListPattern(keyList, pattern1);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 39-40
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addWordPattern(WordKey.Word, pattern0);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		regexContext.addLabelRegex(LabelKey.P_Label, sw_product);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 42-46
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.N_Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.N2_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.Word);
		keyList.add(WordKey.N_Word);
		keyList.add(WordKey.N2_Word);
		regexContext.addWordListPattern(keyList, pattern2);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 48-51
		regexContext.addHeuristicLabel(LabelKey.N2_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.N_Word);
		keyList.add(WordKey.N2_Word);
		regexContext.addWordListPattern(keyList, pattern3);
		regexContext.addLabelRegex(LabelKey.Label, sw_version);
		regexContext.addLabelRegex(LabelKey.N2_Label, no_label);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 53-56
		regexContext.addHeuristicLabel(LabelKey.N3_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.N_Word);
		keyList.add(WordKey.N2_Word);
		keyList.add(WordKey.N3_Word);
		regexContext.addWordListPattern(keyList, pattern3);
		regexContext.addLabelRegex(LabelKey.Label, sw_version);
		regexContext.addLabelRegex(LabelKey.N3_Label, no_label);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 59-60
		regexContext.addHeuristicLabel(LabelKey.N_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.Word);
		keyList.add(WordKey.N_Word);
		regexContext.addWordListPattern(keyList, pattern5);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 61-66
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.P_Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.P2_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.P2_Word);
		keyList.add(WordKey.P_Word);
		keyList.add(WordKey.Word);
		regexContext.addWordListPattern(keyList, pattern6);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 67-69
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.P_Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.P2_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.P2_Word);
		keyList.add(WordKey.P_Word);
		keyList.add(WordKey.Word);
		regexContext.addWordListPattern(keyList, pattern6);
		regexContext.addHeuristicLabel(LabelKey.N2_Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.N_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.N_Word);
		keyList.add(WordKey.N2_Word);
		regexContext.addWordListPattern(keyList, pattern7);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 70-73
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.P_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.P_Word);
		keyList.add(WordKey.Word);
		regexContext.addWordListPattern(keyList, pattern6);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 75-76
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.Word);
		regexContext.addWordListPattern(keyList, pattern11);
		regexContext.addLabelRegex(LabelKey.P_Label, sw_product);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 79-91 --> skipped because they are included in the patternList0
		// Line 92-95 & 108 & 112
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.N_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.Word);
		keyList.add(WordKey.N_Word);
		regexContext.addWordListPattern(keyList, pattern13);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 97-101 & 108 & 112
		regexContext.addHeuristicLabel(LabelKey.Label, CyberHeuristicAnnotator.SW_VERSION);
		regexContext.addHeuristicLabel(LabelKey.N_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.Word);
		keyList.add(WordKey.N_Word);
		regexContext.addWordListPattern(keyList, pattern14);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 105-117
		regexContext.addHeuristicLabel(LabelKey.N2_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.Word);
		keyList.add(WordKey.N_Word);
		List<Pattern> patternList = new ArrayList<Pattern>();
		patternList.add(pattern13);
		patternList.add(pattern14);
		regexContext.addWordPatternLists(keyList, patternList);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.N_Word);
		keyList.add(WordKey.N2_Word);
		regexContext.addWordListPattern(keyList, pattern3);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line 105-117
		regexContext.addHeuristicLabel(LabelKey.N2_Label, CyberHeuristicAnnotator.SW_VERSION);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.Word);
		keyList.add(WordKey.N_Word);
		patternList = new ArrayList<Pattern>();
		patternList.add(pattern13);
		patternList.add(pattern14);
		regexContext.addWordPatternLists(keyList, patternList);
		regexContext.addLabelRegex(LabelKey.Label, no_label);
		keyList = new ArrayList<WordKey>();
		keyList.add(WordKey.N_Word);
		keyList.add(WordKey.N2_Word);
		keyList.add(WordKey.N3_Word);
		regexContext.addWordListPattern(keyList, pattern3);
		regexList.add(regexContext);
		
		regexContext = new RegexContext();
		// Line
		
	}
	
	
	public static void main(String[] args) {
		String exampleText = "Microsoft Windows 7 before SP1 has Oracle Java Runtime Environment cross-site scripting vulnerability in file.php (refer to CVE-2014-1234).";
		EntityLabeler labeler = new EntityLabeler();
		Annotation doc = labeler.getAnnotatedDoc("My Doc", exampleText);
		
		List<CoreMap> sentences = doc.get(SentencesAnnotation.class);
		for ( CoreMap sentence : sentences) {
//			
			for ( CoreLabel token : sentence.get(TokensAnnotation.class)) {
				String word = token.get(TextAnnotation.class);
				if (word.equalsIgnoreCase("Windows") || word.equalsIgnoreCase("7")) {
					token.set(CyberHeuristicAnnotation.class, CyberHeuristicAnnotator.SW_PRODUCT);
				}
				else if (word.equalsIgnoreCase("before") || word.equalsIgnoreCase("SP1")) {
					token.set(CyberHeuristicAnnotation.class, CyberHeuristicAnnotator.SW_VERSION);
				}
				else if (word.equalsIgnoreCase("has") || word.equalsIgnoreCase("in")) {
					token.set(CyberHeuristicAnnotation.class, CyberHeuristicAnnotator.O);
				}
				else if (word.equalsIgnoreCase("cross-site") || word.equalsIgnoreCase("scripting") || word.equalsIgnoreCase("vulnerability")) {
					token.set(CyberHeuristicAnnotation.class, CyberHeuristicAnnotator.VULN_DESC);
				}
				else if (word.equalsIgnoreCase("file.php")) {
					token.set(CyberHeuristicAnnotation.class, CyberHeuristicAnnotator.SW_SYMBOL);
				}
				else if (word.equalsIgnoreCase("CVE-2014-1234")) {
					token.set(CyberHeuristicAnnotation.class, CyberHeuristicAnnotator.VULN_CVE);
				}
//				System.out.println(token.get(TextAnnotation.class) + "\t" + token.get(PartOfSpeechAnnotation.class) + "\t" + token.get(CyberAnnotation.class));
			}
		}

	}

}
