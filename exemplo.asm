.class public YeledClass
.super java/lang/Object

.field private static soma I
.field private static m I

.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public static somaImpares(I)I
	iload 0
	.limit locals 10
	.limit stack 10

	bipush 0
	istore 5  ; salva resultado

	bipush 0
	istore 7  ; salva i

	goto teste
	laco:
	bipush 2
	iload 7  ; carrega i
	imul

	bipush 1
	iadd

	istore 6  ; salva proxImpar

	iload 5  ; carrega resultado
	iload 6  ; carrega proxImpar
	iadd

	istore 5  ; salva resultado

	iload 7  ; carrega i
	bipush 1
	iadd

	istore 7  ; salva i

	teste:
	iload 7  ; carrega i
	iload 4  ; carrega n
	if_icmplt laco


	iload 5  ; carrega resultado
	getstatic java/lang/System/out Ljava/io/PrintStream;
	swap
	invokevirtual java/io/PrintStream/println(I)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "\n"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	iload 5  ; carrega resultado
	ireturn
.end method

.method public static main([Ljava/lang/String;)V
	.limit locals 10
	.limit stack 10

	bipush 9
	putstatic YeledClass/m I ; salva global m

	getstatic YeledClass/m I ; carrega global m
	invokestatic YeledClass/somaImpares(I)I
	return
.end method

