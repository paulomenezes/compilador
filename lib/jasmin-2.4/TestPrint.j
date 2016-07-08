
.class public TestPrint
.super java/lang/Object

; standard initializer (constuctor)
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 4
       .limit stack 3

       ; var #1 - the PrintStream object held in java.lang.System.out
       getstatic java/lang/System/out Ljava/io/PrintStream;
       astore_1
	   
       ; print a string
       aload_1           ; push the PrintStream object
       ldc "Hello World" ; push the parameter (it comes from constant pool, in this case)
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

       ; print an int
       aload_1   ; push the PrintStream object
       bipush 65 ; push the parameter
       invokevirtual java/io/PrintStream/println(I)V

       ; done
       return

.end method
