package visitante.c_ast_ascendente.alex;
import visitante.c_ast_ascendente.errors.GestionErroresTiny;

%%
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode
%public
%cup
%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
  public GestionErroresTiny getGestionErrores(){
    return this.errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

INT = (i|I)(n|N)(t|T)
REAL = (r|R)(e|E)(a|A)(l|L)
BOOL = (b|B)(o|O)(o|O)(l|L)
String = (s|S)(t|T)(r|R)(i|I)(n|N)(g|G)
AND = (a|A)(n|N)(d|D)
OR = (o|O)(r|R)
NOT = (n|N)(o|O)(t|T)
NULL = (n|N)(u|U)(l|L)(l|L)
TRUE = (t|T)(r|R)(u|U)(e|E)
FALSE = (f|F)(a|A)(l|L)(s|S)(e|E)
PROC = (p|P)(r|R)(o|O)(c|C)
IF = (i|I)(f|F)
ELSE = (e|E)(l|L)(s|S)(e|E)
WHILE = (w|W)(h|H)(i|I)(l|L)(e|E)
STRUCT = (s|S)(t|T)(r|R)(u|U)(c|C)(t|T)
NEW = (n|N)(e|E)(w|W)
DELETE = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)
READ = (r|R)(e|E)(a|A)(d|D)
WRITE = (w|W)(r|R)(i|I)(t|T)(e|E)
NL = (n|N)(l|L)
TYPE = (t|T)(y|Y)(p|P)(e|E)
CALL = (c|C)(a|A)(l|L)(l|L)
letra =  ([a-z]|[A-Z])
digitoPositivo  = [1-9]
digito  = ({digitoPositivo}|0)
parteEntera = ({digitoPositivo}{digito}*|0)
parteDecimal =  ({digito}*{digitoPositivo}|0)
parteExponencial = (\e|\E)[\+\-]?{parteEntera}
Identificador  = ({letra}|_)({letra}|{digito}|_)*
Entero = [\+\-]?{parteEntera}
Real = {Entero}(\.{parteDecimal}({parteExponencial})?|{parteExponencial}) 
Cadena = \"[^\"]*\"
POR = \*
ENTRE = \/
MAS = \+
MENOS = \-
MENOR = \<
MAYOR= \>
MENORIGUAL = "<="
MAYORIGUAL = ">="
COMPARACION = "=="
DISTINTO = "!="
ASIGNACION = \=
INIPAR = \(
FINPAR = \) 
INIBLOQUE = \{
FINBLOQUE = \}
ARROBA = \@
FINDECLARACIONES = "&&"
PUNTOYCOMA = \;
INIARRAY = \[
FINARRAY = \]
PUNTERO = \^
MODULO = \%
COMA = \,
REFERENCIA = \&
PUNTO = \.
separador = [ \t\r\b\n]
comentario = ##([^\n])* 

%% 

{Entero}		      {return ops.unidadEntero();}
{Real}			      {return ops.unidadReal();}
{Cadena} 		      {return ops.unidadCadena();}
{INT}			        {return ops.unidadINT();}		
{REAL}			      {return ops.unidadREAL();}
{BOOL}			      {return ops.unidadBOOL();}
{String}		      {return ops.unidadSTRING();}
{AND}			        {return ops.unidadAND();}
{OR}			        {return ops.unidadOR();}
{NOT}			        {return ops.unidadNOT();}
{NULL}			      {return ops.unidadNULL();}
{TRUE}			      {return ops.unidadTRUE();}
{FALSE}			      {return ops.unidadFALSE();}
{PROC}			      {return ops.unidadPROC();}
{IF}			        {return ops.unidadIF();}
{ELSE}			      {return ops.unidadELSE();}
{WHILE}			      {return ops.unidadWHILE();}
{STRUCT}		      {return ops.unidadSTRUCT();}
{NEW}			        {return ops.unidadNEW();}
{DELETE}		      {return ops.unidadDELETE();}
{READ}			      {return ops.unidadREAD();}
{WRITE}			      {return ops.unidadWRITE();}
{NL}			        {return ops.unidadNL();}
{TYPE}			      {return ops.unidadTYPE();}
{CALL}			      {return ops.unidadCALL();}
{Identificador}		{return ops.unidadId();}
{POR}			        {return ops.unidadPOR();}
{ENTRE}			      {return ops.unidadENTRE();}
{MAS}			        {return ops.unidadMAS();}
{MENOS}			      {return ops.unidadMENOS();}
{MENOR}			      {return ops.unidadMENOR();}
{MAYOR}			      {return ops.unidadMAYOR();}
{MENORIGUAL}		  {return ops.unidadMENORIGUAL();}
{MAYORIGUAL}		  {return ops.unidadMAYORIGUAL();}
{COMPARACION}		  {return ops.unidadCOMPARACION();}
{DISTINTO}		    {return ops.unidadDISTINTO();}
{ASIGNACION}		  {return ops.unidadASIGNACION();}
{INIPAR}		      {return ops.unidadINIPAR();}
{FINPAR} 		      {return ops.unidadFINPAR();}
{INIBLOQUE}		    {return ops.unidadINIBLOQUE();} 
{FINBLOQUE}		    {return ops.unidadFINBLOQUE();}
{ARROBA}		      {return ops.unidadARROBA();}
{FINDECLARACIONES}	  {return ops.unidadFINDECLARACIONES();}
{PUNTOYCOMA}		      {return ops.unidadPUNTOYCOMA();}
{INIARRAY}		        {return ops.unidadINIARRAY();}
{FINARRAY}	        	{return ops.unidadFINARRAY();}
{PUNTERO}		          {return ops.unidadPUNTERO();}
{MODULO}		          {return ops.unidadMODULO();}
{COMA}			          {return ops.unidadCOMA();}
{REFERENCIA}		      {return ops.unidadREFERENCIA();}
{PUNTO}			          {return ops.unidadPUNTO();}
{separador} 		      {}
{comentario} 		      {}
[^]                   {ops.error();}
