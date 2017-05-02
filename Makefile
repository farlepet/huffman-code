# Makefile for 16b_subleq

MAINDIR	= $(CURDIR)
SRC	= $(MAINDIR)/src
OBJ = $(MAINDIR)/obj

JSRC = $(wildcard $(SRC)/*.java) $(wildcard $(SRC)/*/*.java)

JFLAGS = -Xlint

all:
	@echo -e "\033[33m \033[1mJAVAC\033[21m\033[0m"
	@mkdir -p $(OBJ)
	@javac $(JFLAGS) $(JSRC) -d $(OBJ)

clean:
	@echo -e "\033[33m \033[1mCleaning\033[0m"
	@rm -rf $(OBJ)