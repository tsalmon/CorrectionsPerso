(* commencez par compiler le fichier interface_td4.ml par      *)
(* ocamlc -c interface_td4.ml                                  *)
(* les deux fichiers produits interface_td4.cmo/.cmi doivent   *)
(* etre dans le meme repertoire que ce fichier.                *)

(* commentez les deux directives ci-dessous si vous souhaitez  *)
(* le compiler - inclure dans ce cas graphics.cma et           *)
(* interface_td4.cmo dans la ligne de compilation :            *)

(* ocamlc -o balayage interface_td4.cmo graphics.cma infographie_td3.ml *)

#load "graphics.cma";;
#load "interface_td4.cmo";;


(* les polygones sont representes par la liste de leurs 
 * sommets dans l'ordre d'entree, de type (int * int) list 
 * (il vous faudra donc faire les conversions necessaires 
 * par float_of_int pour construire les entrees) 
 *)

(* affichage du bord d'un polygone *)
let afficher_bord p = 
  Graphics.draw_poly (Array.of_list p)
;;


(* construction du tableau statique : *)
let tableau_statique p h = 
  let t  = Array.make h [] in 
  let cons x1 y1 x2 y2 =     
    t.(y1) <-
      (float_of_int x1,
       (float_of_int (x2 - x1)) /.
	 (float_of_int (y2 - y1)),
       y2 - y1)::t.(y1)
  in let rec aux p = match p with
      (x,y)::(x',y')::p' ->
	  if y < y' then cons x y x' y';
	  if y > y' then cons x' y' x y;
	  aux ((x',y')::p');
    | _ -> ()
  in aux (p@[List.hd p]); t
;;

(* mise a jour de la liste dynamique :
 * le : la liste dynamique d'entrees 
 * y  : la ligne courante
 * ts : le tableau statique.
 * renvoie la liste mise a jour
 *)
let maj_dyn y le ts =
  List.sort compare 
    (ts.(y) @
       (List.fold_right
	  (fun (x, dx, h) l -> 
	     if h = 1 then l else
	       (x +. dx, dx, h - 1)::l) le []))
;;

(* affichage de la partie interieure du 
 * polygone sur la ligne courante.
 * y  : la ligne courante
 * le : la liste dynamique courante
 *)
let rec affichage y le = match le with
    [] -> ()
  | (x,_,_)::(x',_,_)::le' -> 
      Graphics.moveto (int_of_float x)  y;
      Graphics.lineto (int_of_float x') y;
      affichage y le'
  | _ -> failwith "erreur de gestion"
;;

(* fonction principale.
 * p : un polygone, en (int * int) list
 * h : l'ordonnee maximale de l'ecran
 *)		
let balayage p h = 
  (* affichage du bord en rouge *)
  Graphics.set_color Graphics.red;
  afficher_bord p;
  (* retour a la couleur noire *)
  Graphics.set_color Graphics.black;
  let ts = tableau_statique p h in
  let le = ref [] in
    for y = 0 to h - 1 do
      le := maj_dyn y !le ts;
      affichage y !le
    done
;;

(************************************************************)
(*                         LANCEMENT                        *)
(************************************************************)

(* le lancement du programme se fait via l'appel de la 
 * fonction main ci-dessous, en lui passant votre fonction 
 * de balayage.      
 *)

Interface_td4.main balayage;;
