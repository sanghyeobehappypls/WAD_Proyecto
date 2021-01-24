PGDMP     :                     y            Proyecto    13.1    13.1 /    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16507    Proyecto    DATABASE     f   CREATE DATABASE "Proyecto" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "Proyecto";
                postgres    false            �            1259    16526    autor    TABLE     �   CREATE TABLE public.autor (
    idautor integer NOT NULL,
    nombre character varying(50) NOT NULL,
    paterno character varying(50),
    materno character varying(50),
    pais character varying(50) NOT NULL
);
    DROP TABLE public.autor;
       public         heap    postgres    false            �            1259    16524    autor_idautor_seq    SEQUENCE     �   CREATE SEQUENCE public.autor_idautor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.autor_idautor_seq;
       public          postgres    false    205            �           0    0    autor_idautor_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.autor_idautor_seq OWNED BY public.autor.idautor;
          public          postgres    false    204            �            1259    16518 	   editorial    TABLE     �   CREATE TABLE public.editorial (
    ideditorial integer NOT NULL,
    nombre character varying(50) NOT NULL,
    pais character varying(50)
);
    DROP TABLE public.editorial;
       public         heap    postgres    false            �            1259    16516    editorial_ideditorial_seq    SEQUENCE     �   CREATE SEQUENCE public.editorial_ideditorial_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.editorial_ideditorial_seq;
       public          postgres    false    203            �           0    0    editorial_ideditorial_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.editorial_ideditorial_seq OWNED BY public.editorial.ideditorial;
          public          postgres    false    202            �            1259    16510    genero    TABLE     i   CREATE TABLE public.genero (
    idgenero integer NOT NULL,
    nombre character varying(50) NOT NULL
);
    DROP TABLE public.genero;
       public         heap    postgres    false            �            1259    16508    genero_idgenero_seq    SEQUENCE     �   CREATE SEQUENCE public.genero_idgenero_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.genero_idgenero_seq;
       public          postgres    false    201            �           0    0    genero_idgenero_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.genero_idgenero_seq OWNED BY public.genero.idgenero;
          public          postgres    false    200            �            1259    16534    libro    TABLE     -  CREATE TABLE public.libro (
    idlibro integer NOT NULL,
    titulo character varying(100) NOT NULL,
    edicion character varying(50) NOT NULL,
    idautor integer NOT NULL,
    ideditorial integer NOT NULL,
    idgenero integer NOT NULL,
    npaginas integer NOT NULL,
    anio integer NOT NULL
);
    DROP TABLE public.libro;
       public         heap    postgres    false            �            1259    16532    libro_idlibro_seq    SEQUENCE     �   CREATE SEQUENCE public.libro_idlibro_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.libro_idlibro_seq;
       public          postgres    false    207            �           0    0    libro_idlibro_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.libro_idlibro_seq OWNED BY public.libro.idlibro;
          public          postgres    false    206            �            1259    16563    prestamo    TABLE     �   CREATE TABLE public.prestamo (
    idusuario integer NOT NULL,
    idlibro integer NOT NULL,
    fechainicio date NOT NULL,
    fechatermino date NOT NULL
);
    DROP TABLE public.prestamo;
       public         heap    postgres    false            �            1259    16557    usuario    TABLE     �  CREATE TABLE public.usuario (
    idusuario integer NOT NULL,
    nombre character varying(50) NOT NULL,
    paterno character varying(50) NOT NULL,
    materno character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    nombreusuario character varying(20) NOT NULL,
    claveusuario character varying(20) NOT NULL,
    tipousuario character varying(20) NOT NULL,
    imagen character varying(100)
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            �            1259    16555    usuario_idusuario_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_idusuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.usuario_idusuario_seq;
       public          postgres    false    209            �           0    0    usuario_idusuario_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.usuario_idusuario_seq OWNED BY public.usuario.idusuario;
          public          postgres    false    208            @           2604    16529    autor idautor    DEFAULT     n   ALTER TABLE ONLY public.autor ALTER COLUMN idautor SET DEFAULT nextval('public.autor_idautor_seq'::regclass);
 <   ALTER TABLE public.autor ALTER COLUMN idautor DROP DEFAULT;
       public          postgres    false    205    204    205            ?           2604    16521    editorial ideditorial    DEFAULT     ~   ALTER TABLE ONLY public.editorial ALTER COLUMN ideditorial SET DEFAULT nextval('public.editorial_ideditorial_seq'::regclass);
 D   ALTER TABLE public.editorial ALTER COLUMN ideditorial DROP DEFAULT;
       public          postgres    false    203    202    203            >           2604    16513    genero idgenero    DEFAULT     r   ALTER TABLE ONLY public.genero ALTER COLUMN idgenero SET DEFAULT nextval('public.genero_idgenero_seq'::regclass);
 >   ALTER TABLE public.genero ALTER COLUMN idgenero DROP DEFAULT;
       public          postgres    false    200    201    201            A           2604    16537    libro idlibro    DEFAULT     n   ALTER TABLE ONLY public.libro ALTER COLUMN idlibro SET DEFAULT nextval('public.libro_idlibro_seq'::regclass);
 <   ALTER TABLE public.libro ALTER COLUMN idlibro DROP DEFAULT;
       public          postgres    false    206    207    207            B           2604    16560    usuario idusuario    DEFAULT     v   ALTER TABLE ONLY public.usuario ALTER COLUMN idusuario SET DEFAULT nextval('public.usuario_idusuario_seq'::regclass);
 @   ALTER TABLE public.usuario ALTER COLUMN idusuario DROP DEFAULT;
       public          postgres    false    209    208    209            �          0    16526    autor 
   TABLE DATA           H   COPY public.autor (idautor, nombre, paterno, materno, pais) FROM stdin;
    public          postgres    false    205   �6       �          0    16518 	   editorial 
   TABLE DATA           >   COPY public.editorial (ideditorial, nombre, pais) FROM stdin;
    public          postgres    false    203   8       �          0    16510    genero 
   TABLE DATA           2   COPY public.genero (idgenero, nombre) FROM stdin;
    public          postgres    false    201   G9       �          0    16534    libro 
   TABLE DATA           i   COPY public.libro (idlibro, titulo, edicion, idautor, ideditorial, idgenero, npaginas, anio) FROM stdin;
    public          postgres    false    207   &:       �          0    16563    prestamo 
   TABLE DATA           Q   COPY public.prestamo (idusuario, idlibro, fechainicio, fechatermino) FROM stdin;
    public          postgres    false    210   �;       �          0    16557    usuario 
   TABLE DATA              COPY public.usuario (idusuario, nombre, paterno, materno, email, nombreusuario, claveusuario, tipousuario, imagen) FROM stdin;
    public          postgres    false    209   �;       �           0    0    autor_idautor_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.autor_idautor_seq', 15, true);
          public          postgres    false    204            �           0    0    editorial_ideditorial_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.editorial_ideditorial_seq', 20, true);
          public          postgres    false    202            �           0    0    genero_idgenero_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.genero_idgenero_seq', 1, false);
          public          postgres    false    200            �           0    0    libro_idlibro_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.libro_idlibro_seq', 13, true);
          public          postgres    false    206            �           0    0    usuario_idusuario_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.usuario_idusuario_seq', 3, true);
          public          postgres    false    208            H           2606    16531    autor autor_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (idautor);
 :   ALTER TABLE ONLY public.autor DROP CONSTRAINT autor_pkey;
       public            postgres    false    205            F           2606    16523    editorial editorial_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.editorial
    ADD CONSTRAINT editorial_pkey PRIMARY KEY (ideditorial);
 B   ALTER TABLE ONLY public.editorial DROP CONSTRAINT editorial_pkey;
       public            postgres    false    203            D           2606    16515    genero genero_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (idgenero);
 <   ALTER TABLE ONLY public.genero DROP CONSTRAINT genero_pkey;
       public            postgres    false    201            J           2606    16539    libro libro_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_pkey PRIMARY KEY (idlibro);
 :   ALTER TABLE ONLY public.libro DROP CONSTRAINT libro_pkey;
       public            postgres    false    207            N           2606    16567    prestamo prestamo_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT prestamo_pkey PRIMARY KEY (idusuario, idlibro);
 @   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT prestamo_pkey;
       public            postgres    false    210    210            L           2606    16562    usuario usuario_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (idusuario);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public            postgres    false    209            O           2606    16540    libro libro_idautor_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_idautor_fkey FOREIGN KEY (idautor) REFERENCES public.autor(idautor) ON UPDATE CASCADE ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.libro DROP CONSTRAINT libro_idautor_fkey;
       public          postgres    false    2888    205    207            P           2606    16545    libro libro_ideditorial_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_ideditorial_fkey FOREIGN KEY (ideditorial) REFERENCES public.editorial(ideditorial) ON UPDATE CASCADE ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.libro DROP CONSTRAINT libro_ideditorial_fkey;
       public          postgres    false    207    203    2886            Q           2606    16550    libro libro_idgenero_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_idgenero_fkey FOREIGN KEY (idgenero) REFERENCES public.genero(idgenero) ON UPDATE CASCADE ON DELETE CASCADE;
 C   ALTER TABLE ONLY public.libro DROP CONSTRAINT libro_idgenero_fkey;
       public          postgres    false    201    207    2884            S           2606    16573    prestamo prestamo_idlibro_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT prestamo_idlibro_fkey FOREIGN KEY (idlibro) REFERENCES public.libro(idlibro) ON UPDATE CASCADE ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT prestamo_idlibro_fkey;
       public          postgres    false    207    2890    210            R           2606    16568     prestamo prestamo_idusuario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT prestamo_idusuario_fkey FOREIGN KEY (idusuario) REFERENCES public.usuario(idusuario) ON UPDATE CASCADE ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.prestamo DROP CONSTRAINT prestamo_idusuario_fkey;
       public          postgres    false    2892    209    210            �   ?  x�e�KNA����G�D|@L e㦘)�CO�TOO�q��s1k\7]�t��?2�h)��NɛO¤y�]�=��ri�����Y*1�Ж�+�0p�׎*!s�1ט��`�i��w�g+k�-a�[7��"�Γ�:�9�PRĄ���K���C�� ��
����^�>Ț1N6⪽F�����d�c���Ԍ���d�����u5�TI�a�R4�Ǜ̓�N0��-�ѷ�|��)�R߯�|)��[Ő��z����yt�ɭ��S<Ɯ�%m}��dN~���@��Zl8х�vI-wqJ�`$܆x92�|v���      �   3  x�}��NB1��ӧ�H����OD�W���@+4靹���<��x1�����r��9in^�X��'�l%���K�!��u�lX5������?ׂ�T%�G�aJܿ�j�ȸ��9�i�Pu`�D�
�=��w;G��'ů�&����,�O�>�cG)k���0�`�m�t =sV��N.��'���]�P08��&�6S��<m�`�m�G�6��ʺ�E��r�N*���RB�*����b���r6M�Ү�Ƅ/uPY�Rm��k��أW�0��Ei��9�:LQ�T8g��?NXZ��tR{�)�> :��@      �   �   x�5�KN�@@��)8�_��-�&���nF�q䙩�q8@W!�H��Y����g�N���X��)�9lK�]Z��^(�>�v�Xp{E3%\C]Q�@���2��ਦ��<�Ft3x���n�ztj9p4�୤�bts[S5�[�[^8�$Kx��v���S�T�����5s�a�aTJ�� ���U38qK�Y����GD�#zQm      �   \  x�e�MJ1�וSĽ��O����ݔ����#�a��p!s�b&q�ЋZ�����f��֛����>`Wt���.�$���ML	I8l�چ���Y-/jAZ؆��QF�:L���B�z
��O��"]�?��qL�-F��^�,�]�+y�"=|�_=L肿���b`	!c"%c2C<X�C�x������R�x=���&\>S8m��Ѩ�t,$,��Ed6Bb����8[�_�G��əX���x��{���-���jSy��.�!������/c"�����k��̕q����2'����9ҭ���~�(�$�v�q������-�_B�%��7      �   (   x�3�44�4202�5 "$&��9�kgp��qqq 	�      �   �   x�m�Aj�@EךS�&�:��%!4�`����d��"�Q9�/։]h]}���?=�[�;�����Չ���$��8�(�n*�����$@NX�����&'��]n��;[T7�Z�٤��E�L�e�H�����c�Db�b#
]�z�^]���6u���'��J�����.�C#^=�?��n���(/��k�=�e�l�j�4��H;Ƙo`�je     