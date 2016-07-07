.class public YeledClass
.super java/lang/Object

.field private static f I
.field private static g I

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

	invokestatic YeledClass/extra()V
	return
.end method

.method public static main([Ljava/lang/String;)V
	.limit locals 10
	.limit stack 10

	bipush 10
	istore 4  ; salva n

	bipush 10
	putstatic YeledClass/f I ; salva global f

	bipush 5
	iload 4  ; carrega n
	imul

	putstatic YeledClass/f I ; salva global f

	iload 4  ; carrega n
	bipush 2
	idiv

	bipush 2
	imul

	istore 3  ; salva nRebuilt

	iload 4  ; carrega n
	iload 3  ; carrega nRebuilt
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
	bipush 2
	getstatic YeledClass/f I ; carrega global f
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
	invokestatic YeledClass/outro()V
	return
.end method

