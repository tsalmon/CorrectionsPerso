"""
Ce probleme correspond a celui du sac a dos
Pour faire toutes les combinaisons possible des elements de E afin d'obtenir V, 
on va prendre les elements un par un, et a chaque fois on ajoute les nouvelles combinaisons
parmi celles qu'on a deja ajoute en leur additionnant la valeur du nouvel element
"""
def possible(E, V):
	p = [[False] * (V+2) for i in E]

	for i in range(0, len(E)):
		p[i][0] = True# correspond a la combinaison vide, car i peut etre egal a 0
		X = E[i]
		if X <= V:
			for k in range(i, len(E)):
				p[k][X] = True

		for j in range(V-1, 1, -1):
			if (p[i][j] == True) and (j != X) and (X + j <= V):
				for k in range(i, len(E)):
					p[k][j+X] = True

	"""
	s = "	"
	for i in range(0, V+1):
		s += str(i) + "	"
	print s

	for i in range(0, len(E)):
		s = str(E[i]) + "	"
		for j in range(0, V+1):
			s += str(p[i][j]) + "	"
		print s
	"""
	return p[len(E)-1][V]


E = [2, 8, 3, 7]
V = 14
print possible(E, V)
