grammar Compute;
fragment DIGIT: [0-9];
fragment A: [aA];
fragment D: [dD];
fragment S: [sS];
fragment U: [uU];
fragment B: [bB];
fragment M: [mM];
fragment L: [lL];
fragment I: [iI];
fragment V: [vV];

K_ADD: A D D;
K_SUB: S U B;
K_MUL: M U L;
K_DIV: D I V;

INTEGER: DIGIT | [1-9] DIGIT+;

K_CMD: 'calc';

add: K_CMD INTEGER K_ADD INTEGER;
sub: K_CMD INTEGER K_SUB INTEGER;
mul: K_CMD INTEGER K_MUL INTEGER;
div: K_CMD INTEGER K_DIV INTEGER;

WS: [ \t\r\n]+ -> skip;