
.class public Exemplo1
.super java/lang/Object

.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       .limit locals 3
       .limit stack 5
	   
	   bipush 15
	   istore 1
	   
	   bipush 3
	   istore 2

   	   iload 1
   	   iload 2
	   
       if_icmple parteElse

       getstatic java/lang/System/out Ljava/io/PrintStream;
       ldc "Maior"
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
       goto parteDepois
	   
	parteElse:
       getstatic java/lang/System/out Ljava/io/PrintStream;
       ldc "Menor ou igual"
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	parteDepois:
       return

.end method
