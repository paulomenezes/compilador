.class public YeledClass
.super java/lang/Object

.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public static extra()V
	.limit locals 10
	.limit stack 10

	bipush 10
	getstatic java/lang/System/out Ljava/io/PrintStream;
	swap
	invokevirtual java/io/PrintStream/println(I)V
	return
.end method

.method public static outro()V
	.limit locals 10
	.limit stack 10

	invokevirtual YeledClass/extra()V
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
	ldc "P"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "A"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "R"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	bipush 1
	bipush 2
	imul

	getstatic java/lang/System/out Ljava/io/PrintStream;
	swap
	invokevirtual java/io/PrintStream/println(I)V
	goto parteDepois

	parteElse:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "I"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "M"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "P"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "A"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "R"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	parteDepois:
	invokevirtual YeledClass/outro()V
	return
.end method

