SRC_DIR = src
OUT_DIR = target/classes

SRC_FLS = $(shell find $(SRC_DIR) -name "*.java")


.DEFAULT_GOAL := build

debug:
	@echo $(SRC_FLS)

build: $(SRC_DIR)
	mkdir -p $(OUT_DIR)
	mvn package

.PHONY: clean run
clean:
	-rm -rf $(OUT_DIR)/*
	-rm -f target/*.jar

run:
	java -jar  target/*.jar