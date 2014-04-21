L = [[0, 1, 3, 7], [0, 4, 2, 6, 3], [5, 2, 3]]
ns = 8

def print_matrice(matrice):
	for i in range(0, len(matrice)):
		string = ""
		for j in range(0, len(matrice[i])):
			string += str(matrice[i][j]) + "	"
		print string

for i in range(0, len(L)):
	#init
	Direct = [[ float("inf") for width in range(0, ns)] for heigth in range(0, ns)]
	for ver in range(0, ns):
		Direct[ver][ver] = 0

	#calcul
	for u in range(0, len(L[i])):
		for v in range(0, len(L[i])):
			Direct[ L[i][u] ][ L[i][v] ] = abs(u-v) * 90

	#affichage
	print_matrice(Direct)
	print ""