package alex;

%%
%line
%column
%class AnalizadorLexicoTinyFlex
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra =  [a-z,A-Z]
digitoPositivo  = [1-9]
digito  = {digitoPositivo} | 0
parteEntera = ({digitoPositivo} {digito}* | 0)
parteDecimal =  ({digito}* {digitoPositivo} | 0)
parteExponencial = (\e | \E) [\+, \-]? {parteEntera}
Identificador  = ({letra} |  _ ) ({letra} | {digito} | _ )*
Entero = [\+,\-]? {parteEntera}
Real = {Entero} (\.{parteDecimal}({parteExponencial})? | {parteExponencial}) 
Cadena = ”(^”)*” 
INT = (i | I)(n | N)(t | T)
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
POR = \*
ENTRE = /
MAS = \+
MENOS = \-
MENOR = <
MAYOR= >
MENORIGUAL = <\=
MAYORIGUAL = >\=
COMPARACIÓN = \=\=
DISTINTO = !\=
ASIGNACION = \=
INIPAR = \(
FINPAR = \) 
INIBLOQUE = {
FINBLOQUE = }
ARROBA = @
FINDECLARACIONES = &&
PUNTOYCOMA = ;
INIARRAY = \[
FINARRAY = \]
PUNTERO = \^
MÓDULO = %
COMA = \,
REFERENCIA= &
PUNTO = .
EOF = EOF
separador = [  ,\t,\r,\b,\n]
comentario = ##([^\n,EOF])*

%% 

{Identificador}		{return ops.unidadId();}
{Entero}		{return ops.unidadEntero();}
{Real}			{return ops.unidadReal();}
{Cadena} 		{return ops.unidadCadena();}
{INT}			{return ops.unidadINTP();}		
{REAL}			{return ops.unidadREALP();}
{BOOL}			{return ops.unidadBOOLP();}
{String}		{return ops.unidadSTRINGP();}
{AND}			{return ops.unidadAND();}
{OR}			{return ops.unidadOR();}
{NOT}			{return ops.unidadNOT();}
{NULL}			{return ops.unidadNULL();}
{TRUE}			{return ops.unidadTRUE();}
{FALSE}			{return ops.unidadFALSE();}
{PROC}			{return ops.unidadPROC();}
{IF}			{return ops.unidadIF();}
{ELSE}			{return ops.unidadELSE();}
{WHILE}			{return ops.unidadWHILE();}
{STRUCT}		{return ops.unidadSTRUCT();}
{NEW}			{return ops.unidadNEW();}
{DELETE}		{return ops.unidadDELETE();}
{READ}			{return ops.unidadREAD();}
{WRITE}			{return ops.unidadWRITE();}
{NL}			{return ops.unidadNL();}
{TYPE}			{return ops.unidadTYPE();}
{CALL}			{return ops.unidadCALL();}
{POR}			{return ops.unidadPOR();}
{ENTRE}			{return ops.unidadENTRE();}
{MAS}			{return ops.unidadMAS();}
{MENOS}			{return ops.unidadMENOS();}
{MENOR}			{return ops.unidadMENOR();}
{MAYOR}			{return ops.unidadMAYOR();}
{MENORIGUAL}		{return ops.unidadMENORIGUAL();}
{MAYORIGUAL}		{return ops.unidadMAYORIGUAL();}
{COMPARACIÓN}		{return ops.unidadCOMPARACIÓN();}
{DISTINTO}		{return ops.unidadDISTINTO();}
{ASIGNACION}		{return ops.unidadASIGNACION();}
{INIPAR}		{return ops.unidadINIPAR();}
{FINPAR} 		{return ops.unidadFINPAR();}
{INIBLOQUE}		{return ops.unidadINIBLOQUE();} 
{FINBLOQUE}		{return ops.unidadFINBLOQUE();}
{ARROBA}		{return ops.unidadARROBA();}
{FINDECLARACIONES}	{return ops.unidadFINDECLARACIONES();}
{PUNTOYCOMA}		{return ops.unidadPUNTOYCOMA();}
{INIARRAY}		{return ops.unidadINIARRAY();}
{FINARRAY}		{return ops.unidadFINARRAY();}
{PUNTERO}		{return ops.unidadPUNTERO();}
{MÓDULO}		{return ops.unidadMÓDULO();}
{COMA}			{return ops.unidadCOMA();}
{REFERENCIA}		{return ops.unidadREFERENCIA();}
{PUNTO}			{return ops.unidadPUNTO();}
{EOF}			{return ops.unidad();}	
{separador} 		{}
{comentario} 		{}
[^]                     {ops.error();}  ??????