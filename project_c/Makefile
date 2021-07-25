SRC = src
OUT = out

CC = gcc
CFLAGS = -W -Wall -Wextra -O2 -D_GNU_SOURCE -std=c99 `pkg-config --cflags --libs glib-2.0`
VFLAGS =  --leak-check=full --show-leak-kinds=all --track-origins=no --verbose

FILES := $(wildcard $(SRC)/*.c)
OBJ := $(patsubst $(SRC)%,$(OUT)%,$(patsubst %.c,%.o,$(FILES)))

EXEC = program
LOG = memlog.txt

$(EXEC): $(OBJ)
	@$(CC) $(OBJ) -o $(OUT)/$@ $(CFLAGS)

$(OBJ): out/%.o : src/%.c                 #isto funciona como uma espécie de foreach
	@$(CC) -c $< -o $@ $(CFLAGS)  #LHS é o target, RHS é o requisito

.PHONY: clean
clean:
	-rm -f $(OBJ)
	-rm -f $(OUT)/$(EXEC)
	-rm -f $(OUT)/$(LOG)

.PHONY: run
run:
	@$(OUT)/$(EXEC)

.PHONY: $(LOG)
$(LOG):
	@valgrind $(VFLAGS) --log-file=$(OUT)/$@ $(OUT)/$(EXEC)

#--run-libc-freeres=no <- poderá ser importante esta flag para o memcheck
