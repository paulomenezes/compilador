.class public YeledClass
.super java/lang/Object

.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public static main([Ljava/lang/String;)V
	.limit locals 10
	.limit stack 10

	bipush 10
	istore 1  ; salva n

	iload 1  ; carrega n
	bipush 2
	idiv

	bipush 2
	imul

	istore 2  ; salva nRebuilt

	iload 1  ; carrega n
	iload 2  ; carrega nRebuilt
	if_icmpne parteElse

	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "PAR"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	goto parteDepois

	parteElse:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "IMPAR"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	parteDepois:
	return
.end method

.method public static extra([Ljava/lang/String;)V
	.limit locals 10
	.limit stack 10

	return
.end method

.method public static outro([Ljava/lang/String;)V
	.limit locals 10
	.limit stack 10

	bipush 0
	istore 1

	return
.end method

