package com.matcracker.PMManagerServers.plugincreator;

import com.matcracker.PMManagerServers.utility.Utility;

public class CodeUtility {
	/**
	 * @param type
	 * @return
	 */
	public static CodeStructures toCodeStructure(int type){
		if(type < 0 || type > CodeStructures.values().length) return CodeStructures.NOTHING;
		
		return CodeStructures.values()[type];
	}
	
	/**
	 * @param type
	 * @param condition
	 * @return
	 */
	public static String getStructure(CodeStructures type){
		String cond = "", result = "", code = "", total = "", variable = "";
		switch(type){
			case IF:
				cond = Utility.readString("Write condition: ", "[IF]");
				do{
					result = Utility.readString("Write result of IF structure: ", "[IF] Write 'stop' for go out the IF results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				
				String elsee = "", total2 = "";
				do{
					elsee = Utility.readString("Write result of ELSE structure: ", "[ELSE] Write 'n' if you don't want add ELSE structure or write 'stop' for go out the ELSE results");
					if(elsee.equalsIgnoreCase("stop") || elsee.equalsIgnoreCase("n")) break;
					
					total2 = total2 + "\n\t\t\t" + elsee;
				}while(!elsee.equalsIgnoreCase("stop"));
				
				//if(condition){ result;
				code = code + 
						"if(" + cond + "){\n" + 
						total;
				//}else{ result;
				if(!elsee.equalsIgnoreCase("n")){
					code +=	"\n\t\t}else{\n" + 
							total2;
				}
				//}
				code += "\n\t\t}";
				return code;
				
			case ELSE_IF:
				String elseif = "";
				cond = Utility.readString("Write condition: ", "[IF]");
				do{
					result = Utility.readString("Write result of IF structure: ", "[IF] Write 'stop' for go out the IF results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				//if(condition){ result;
				code = code + 
						"if(" + cond + "){\n" + 
						total;
				
				do{
					total2 = "";
					elseif = Utility.readString("Write condition: ", "[ELSEIF] Write 'stop' for go out ELSE_IF structure");
					if(elseif.equalsIgnoreCase("stop")) break;
					String result_elseif = "";
					
					do{
						result_elseif = Utility.readString("Write result of ELSE_IF structure: ", null);
						if(result_elseif.equalsIgnoreCase("stop")) break;
						total2 = total2 + "\n\t\t\t" + result_elseif;
					}while(!result_elseif.equalsIgnoreCase("stop"));
					
					code = code + 
							"\n\t\t}elseif(" + elseif + "){" + 
							total2;
				}while(!elseif.equalsIgnoreCase("stop"));

				String total3 = "";
				do{
					elsee = Utility.readString("Write result of ELSE structure: ", "[ELSE] Write 'n' if you don't want add ELSE structure or write 'stop' for go out the ELSE results");
					if(elsee.equalsIgnoreCase("stop") || elsee.equalsIgnoreCase("n")) break;
					
					total3 = total3 + "\n\t\t\t" + elsee;
				}while(!elsee.equalsIgnoreCase("stop"));

				//}elseif(condition){ result;
				if(!elsee.equalsIgnoreCase("n")){
					//}else{ result;
					code = code +
							"\n\t\t}else{\n" + 
							total3;
				}
				//}
				code = code + "\n\t\t}";
				return code;
				
			case FOR:
				variable = Utility.readString("Select counter name: ", "[Example: $i, $c]");
				String start = Utility.readString("Starting value of counter: ", null);
				cond = Utility.readString("Write condition: ", "[FOR]");
				do{
					result = Utility.readString("Write result of FOR structure: ", "Write 'stop' for go out the results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				
				//for(variable = start; cond; variable++){ result; }
				code = code + 
						"for(" + variable + " = " + start + "; " + cond + "; "+ variable + "++){\n" + 
							"\t\t" + total +
						"\n\t\t}";
				return code;
				
			case WHILE:
				cond = Utility.readString("Write condition: ", "[WHILE]");
				do{
					result = Utility.readString("Write result of WHILE structure: ", "Write 'stop' for go out the results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				//while(condition){ result; }
				code = code + 
						"while(" + cond + "){\n" +
						total +
						"\n\t\t}";
				return code;
				
			case DO_WHILE:
				cond = Utility.readString("Write condition: ", "[DO_WHILE]");
				do{
					result = Utility.readString("Write result of DO_WHILE structure: ", "Write 'stop' for go out the results");
					if(result.equalsIgnoreCase("stop")) break;
					
					total = total + "\n\t\t\t" + result;
				}while(!result.equalsIgnoreCase("stop"));
				//do{ result; }while(cond);
				code = code + 
						"do{\n" + 
						total +
						"\n\t\t}while(" + cond + ");";
				return code;
				
			case NOTHING:
				return "";
			default:
				return "Type not valid!";
		}
	}
	
	public enum CodeStructures{
		IF,
		ELSE_IF,
		FOR,
		WHILE,
		DO_WHILE,
		NOTHING;
	}
}
