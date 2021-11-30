import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

	public static final int SIZE = 100;
	static int reac_num = SIZE*SIZE*1e4;
	static int bio_num = 3;
	static int para_num = 21;
	static int init_num = 30;

	public static int[] count_cells(Cell[][] cells) {
		int count[] = new int[bio_num];
		for (int i = 0; i < bio_num; i++) {
			count[i] = 0;
		}
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (cells[i][j].a == 1) {
					count[0]++;
				}
				if (cells[i][j].a == 2) {
					count[1]++;
				}
				if (cells[i][j].a == 3) {
					count[2]++;
				}
			}
		}
		return count;
	}

	public static int count_cca(Cell[][] cells, int ri, int rj) {
		int count_cca = 0;
		int[] nei_i = new int[3];
		nei_i[0] = ri;
		nei_i[1] = ri + 1;
		nei_i[2] = ri - 1;

		int[] nei_j = new int[3];
		nei_j[0] = rj;
		nei_j[1] = rj + 1;
		nei_j[2] = rj - 1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cells[nei_i[i]][nei_j[j]].get_a() == 3) {
					count_cca++;
				}
			}
		}

		return count_cca;
	}

	public static int count_cca_t(Cell[][] cells, int ri, int rj) {
		int count_cca = 0;
		int[] nei_i = new int[3];
		int[] nei_j = new int[3];

		nei_i[0] = ri;
		nei_i[1] = ri + 1;
		nei_i[2] = ri - 1;

		nei_j[0] = rj;
		nei_j[1] = rj + 1;
		nei_j[2] = rj - 1;

		if (ri == 0 && 0 < rj && rj < SIZE - 1) {
			nei_i[2] = SIZE - 1;
		}

		if (ri == SIZE - 1 && 0 < rj && rj < SIZE - 1) {
			nei_i[1] = 0;
		}
		if (0 < ri && ri < SIZE - 1 && rj == 0) {
			nei_j[2] = SIZE - 1;
		}

		if (0 < ri && ri < SIZE - 1 && rj == SIZE - 1) {
			nei_j[1] = 0;
		}

		if (ri == 0 && rj == 0) {
			nei_i[2] = SIZE - 1;
			nei_j[2] = SIZE - 1;
		}

		if (ri == 0 && rj == SIZE - 1) {
			nei_i[2] = SIZE - 1;
			nei_j[1] = 0;
		}

		if (ri == SIZE - 1 && rj == 0) {
			nei_i[1] = 0;
			nei_j[2] = SIZE - 1;
		}

		if (ri == SIZE - 1 && rj == SIZE - 1) {
			nei_i[1] = 0;
			nei_j[1] = 0;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cells[nei_i[i]][nei_j[j]].get_a() == 3) {
					count_cca++;
				}
			}
		}
		return count_cca;
	}

	public static int[] neighbors(int ri, int rj) {
		Random r = new Random();
		int defs = r.nextInt(8);

		int[] nei = new int[2];
		nei[0] = ri;
		nei[1] = rj;

		if (defs == 0) {
			nei[1] = rj + 1;
		}

		if (defs == 1) {
			nei[1] = rj - 1;
		}

		if (defs == 2) {
			nei[0] = ri + 1;
		}

		if (defs == 3) {
			nei[0] = ri - 1;
		}

		if (defs == 4) {
			nei[0] = ri + 1;
			nei[1] = rj + 1;
		}

		if (defs == 5) {
			nei[0] = ri - 1;
			nei[1] = rj - 1;
		}

		if (defs == 6) {
			nei[0] = ri + 1;
			nei[1] = rj - 1;
		}

		if (defs == 7) {
			nei[0] = ri - 1;
			nei[1] = rj + 1;
		}
		return nei;
	}

	public static int[] neighbors_t(int ri, int rj) {
		Random r = new Random();
		int defs = r.nextInt(8);

		int[] nei_i = new int[3];
		int[] nei_j = new int[3];

		nei_i[0] = ri;
		nei_i[1] = ri + 1;
		nei_i[2] = ri - 1;

		nei_j[0] = rj;
		nei_j[1] = rj + 1;
		nei_j[2] = rj - 1;

		if (ri == 0 && 0 < rj && rj < SIZE - 1) {
			nei_i[2] = SIZE - 1;
		}

		if (ri == SIZE - 1 && 0 < rj && rj < SIZE - 1) {
			nei_i[1] = 0;
		}

		if (0 < ri && ri < SIZE - 1 && rj == 0) {
			nei_j[2] = SIZE - 1;
		}

		if (0 < ri && ri < SIZE - 1 && rj == SIZE - 1) {
			nei_j[1] = 0;
		}

		if (ri == 0 && rj == 0) {
			nei_i[2] = SIZE - 1;
			nei_j[2] = SIZE - 1;
		}

		if (ri == 0 && rj == SIZE - 1) {
			nei_i[2] = SIZE - 1;
			nei_j[1] = 0;
		}

		if (ri == SIZE - 1 && rj == 0) {
			nei_i[1] = 0;
			nei_j[2] = SIZE - 1;
		}

		if (ri == SIZE - 1 && rj == SIZE - 1) {
			nei_i[1] = 0;
			nei_j[1] = 0;
		}

		int[] nei = new int[2];
		nei[0] = ri;
		nei[1] = rj;

		if (defs == 0) {
			nei[1] = nei_j[1];
		}

		if (defs == 1) {
			nei[1] = nei_j[2];
		}

		if (defs == 2) {
			nei[0] = nei_i[1];
		}

		if (defs == 3) {
			nei[0] = nei_i[2];
		}

		if (defs == 4) {
			nei[0] = nei_i[1];
			nei[1] = nei_j[1];
		}

		if (defs == 5) {
			nei[0] = nei_i[2];
			nei[1] = nei_j[2];
		}

		if (defs == 6) {
			nei[0] = nei_i[1];
			nei[1] = nei_j[2];
		}

		if (defs == 7) {
			nei[0] = nei_i[2];
			nei[1] = nei_j[1];
		}
		return nei;
	}

	public static int[] parseInts(String[] str){
		int[] x =new int[str.length];
		for (int i=0; i<str.length; i++){
			x[i]=Integer.parseInt(str[i]);
		}
		return x;
	}

	public static void main(String[] args) {
		try {

			int time=0;
			time =Integer.parseInt(args[0]);

			int result_u[][] = new int[para_num][init_num + 1];
			for (int i = 0; i < para_num; i++) {
				for (int j = 0; j < init_num + 1; j++) {
					result_u[i][j] = 0;
				}
			}
			for (int i = 0; i < para_num; i++) {
				result_u[i][0] = i;
			}

			int result_a[][] = new int[para_num][init_num + 1];
			for (int i = 0; i < para_num; i++) {
				for (int j = 0; j < init_num + 1; j++) {
					result_a[i][j] = 0;
				}
			}
			for (int i = 0; i < para_num; i++) {
				result_a[i][0] = i;
			}

			int result_c[][] = new int[para_num][init_num + 1];
			for (int i = 0; i < para_num; i++) {
				for (int j = 0; j < init_num + 1; j++) {
					result_c[i][j] = 0;
				}
			}
			for (int i = 0; i < para_num; i++) {
				result_c[i][0] = i;
			}

			for (int pn = 0; pn < para_num; pn++) {
				for (int in = 0; in < init_num; in++) {

					FileReader frp = new FileReader(
									+ String.valueOf(pn) + ".txt"); 
					BufferedReader brp = new BufferedReader(frp);
					String string_p[] = new String[8];
					for (int i = 0; i < 8; i++) {
						string_p[i] = brp.readLine();
					}
					brp.close();

					FileReader fri = new FileReader(
									+ String.valueOf(in) + ".txt");
					BufferedReader bri = new BufferedReader(fri);

					int init_place[][] = new int[SIZE][SIZE];

					for (int i = 0; i < SIZE; i++) {
					String str =bri.readLine();
						init_place[i] = parseInts(str.split("\t"));
					}
					bri.close();

					double para[] = new double[8];
					for (int i = 0; i < 8; i++) {
						para[i] = 0;
					}
					para[0] = Double.parseDouble(string_p[0]);
					para[1] = Double.parseDouble(string_p[1]);
					para[2] = Double.parseDouble(string_p[2]);
					para[3] = Double.parseDouble(string_p[3]);
					para[4] = Double.parseDouble(string_p[4]);
					para[5] = Double.parseDouble(string_p[5]);
					para[6] = Double.parseDouble(string_p[6]);
					para[7] = Double.parseDouble(string_p[7]);

					// set init cells & graph 
					int graph_num = 0;
					graph_num = 1;
					int result[][] = new int[graph_num + 1][bio_num];
					for (int i = 0; i < graph_num + 1; i++) {
						for (int j = 0; j < bio_num; j++) {
							result[i][j] = 0;
						}
					}

					Random r = new Random();
					Cell cells[][] = new Cell[SIZE][SIZE];
					for (int i = 0; i < SIZE; i++) {
						for (int j = 0; j < SIZE; j++) {
							cells[i][j] = new Cell();
						}
					}

					for (int i = 0; i < SIZE; i++) {
						cells[i][0].set_type(1);
						cells[i][SIZE - 1].set_type(1);
					}

					for (int j = 0; j < SIZE; j++) {
						cells[0][j].set_type(1);
						cells[SIZE - 1][j].set_type(1);
					}

					for (int i = 1; i < SIZE - 1; i++) {
						for (int j = 1; j < SIZE - 1; j++) {
							cells[i][j].set_type(0);
						}
					}

					for (int i = 0; i < SIZE; i++) {
						for (int j = 0; j < SIZE; j++) {

								cells[i][j].set_a(init_place[i][j]);
							}
						}

					int [] init_place_r = new int [4];
					int [] count_c= new int[bio_num];
					count_c=count_cells(cells);
					init_place_r[0]=(SIZE*SIZE)-count_c[0]-count_c[1]-count_c[2];
					init_place_r[1]=count_c[0];
					init_place_r[2]=count_c[1];
					init_place_r[3]=count_c[2];

					result[0][0] = count_c[0];
					result[0][1] = count_c[1];
					result[0][2] = count_c[2];

					for (int i = 1; i < reac_num + 1; i++) {

						// two body reaction
						int defr = r.nextInt(SIZE * SIZE);
						int ri = defr / (SIZE);
						int rj = defr % (SIZE);

						int[] k = new int[2];
						k = neighbors(ri, rj);

						int[] l = new int[2];
						l = neighbors_t(ri, rj);

						if (cells[ri][rj].get_a() == 1
								&& cells[ri][rj].get_type() == 0
								&& cells[k[0]][k[1]].get_a() == 2) {

							cells[k[0]][k[1]].calc0(para[0], para[6]
									+ count_cca(cells, ri, rj) * para[7]);
							cells[k[0]][k[1]].update();
						}

						if (cells[ri][rj].get_a() == 1
								&& cells[ri][rj].get_type() == 1
								&& cells[l[0]][l[1]].get_a() == 2) {

							cells[l[0]][l[1]].calc0(para[0], para[6]
									+ count_cca_t(cells, ri, rj) * para[7]);
							cells[l[0]][l[1]].update();
						}

						if (cells[ri][rj].get_a() == 2
								&& cells[ri][rj].get_type() == 0
								&& cells[k[0]][k[1]].get_a() == 1
								&& cells[k[0]][k[1]].get_type() == 0) {

							cells[ri][rj].calc0(para[0],
									para[6] + count_cca(cells, k[0], k[1])
											* para[7]);
							cells[ri][rj].update();
						}

						if (cells[ri][rj].get_a() == 2
								&& cells[ri][rj].get_type() == 0
								&& cells[k[0]][k[1]].get_a() == 1
								&& cells[k[0]][k[1]].get_type() == 1) {

							cells[ri][rj].calc0(para[0],
									para[6] + count_cca_t(cells, k[0], k[1])
											* para[7]);
							cells[ri][rj].update();
						}

						if (cells[ri][rj].get_a() == 2
								&& cells[ri][rj].get_type() == 1
								&& cells[l[0]][l[1]].get_a() == 1
								&& cells[l[0]][l[1]].get_type() == 0) {

							cells[ri][rj].calc0(para[0],
									para[6] + count_cca(cells, l[0], l[1])
											* para[7]);
							cells[ri][rj].update();
						}

						if (cells[ri][rj].get_a() == 2
								&& cells[ri][rj].get_type() == 1
								&& cells[l[0]][l[1]].get_a() == 1
								&& cells[l[0]][l[1]].get_type() == 1) {

							cells[ri][rj].calc0(para[0],
									para[6] + count_cca_t(cells, l[0], l[1])
											* para[7]);
							cells[ri][rj].update();
						}

						if (cells[ri][rj].get_a() == 0
								&& cells[ri][rj].get_type() == 0
								&& cells[k[0]][k[1]].get_a() == 2) {
							cells[ri][rj].calc1(para[1]);
							cells[ri][rj].update();
						}

						if (cells[ri][rj].get_a() == 0
								&& cells[ri][rj].get_type() == 1
								&& cells[l[0]][l[1]].get_a() == 2) {
							cells[ri][rj].calc1(para[1]);
							cells[ri][rj].update();
						}

						if (cells[ri][rj].get_a() == 2
								&& cells[ri][rj].get_type() == 0
								&& cells[k[0]][k[1]].get_a() == 0) {
							cells[k[0]][k[1]].calc1(para[1]);
							cells[k[0]][k[1]].update();
						}

						if (cells[ri][rj].get_a() == 2
								&& cells[ri][rj].get_type() == 1
								&& cells[l[0]][l[1]].get_a() == 0) {
							cells[l[0]][l[1]].calc1(para[1]);
							cells[l[0]][l[1]].update();
						}

						if (cells[ri][rj].get_a() == 0
								&& cells[ri][rj].get_type() == 0
								&& cells[k[0]][k[1]].get_a() == 3) {
							cells[ri][rj].calc2(para[2]);
							cells[ri][rj].update();
						}

						if (cells[ri][rj].get_a() == 0
								&& cells[ri][rj].get_type() == 1
								&& cells[l[0]][l[1]].get_a() == 3) {
							cells[ri][rj].calc2(para[2]);
							cells[ri][rj].update();
						}

						if (cells[ri][rj].get_a() == 3
								&& cells[ri][rj].get_type() == 0
								&& cells[k[0]][k[1]].get_a() == 0) {
							cells[k[0]][k[1]].calc2(para[2]);
							cells[k[0]][k[1]].update();
						}

						if (cells[ri][rj].get_a() == 3
								&& cells[ri][rj].get_type() == 1
								&& cells[l[0]][l[1]].get_a() == 0) {
							cells[l[0]][l[1]].calc2(para[2]);
							cells[l[0]][l[1]].update();
						}

						// one body reaction
						int defro = r.nextInt(SIZE * SIZE);
						int rri = defro / SIZE;
						int rrj = defro % SIZE;

						if (cells[rri][rrj].get_a() == 1) {
							cells[rri][rrj].calc3(para[3]);
							cells[rri][rrj].update();
						}

						if (cells[rri][rrj].get_a() == 2) {
							cells[rri][rrj].calc4(para[4]);
							cells[rri][rrj].update();
						}

						if (cells[rri][rrj].get_a() == 3) {
							cells[rri][rrj].calc5(para[5]);
							cells[rri][rrj].update();
						}

						if (i % (reac_num / graph_num) == 0) {
							result[i / (reac_num / graph_num)][0] = count_cells(cells)[0];
							result[i / (reac_num / graph_num)][1] = count_cells(cells)[1];
							result[i / (reac_num / graph_num)][2] = count_cells(cells)[2];
						}

					}

					result_u[pn][in + 1] = result[graph_num][0];
					result_a[pn][in + 1] = result[graph_num][1];
					result_c[pn][in + 1] = result[graph_num][2];
				}
			}

			File ufile = new File("result_u"+time+".txt");
			ufile.createNewFile();
			FileWriter ufilewriter = new FileWriter(ufile, true);
			BufferedWriter ubw = new BufferedWriter(ufilewriter);
			ubw.write("para\t");
			for (int i = 0; i < init_num; i++) {
				ubw.write("ini" + i + "\t");
			}
			ubw.write("\n");
			for (int i = 0; i < para_num; i++) {
				for (int j = 0; j < init_num + 1; j++) {
					ubw.write(result_u[i][j] + "\t");
				}
				ubw.write("\n");
			}
			ubw.close();

			File afile = new File("result_a"+time+".txt");
			afile.createNewFile();
			FileWriter afilewriter = new FileWriter(afile, true);
			BufferedWriter abw = new BufferedWriter(afilewriter);
			abw.write("para\t");
			for (int i = 0; i < init_num; i++) {
				abw.write("ini" + i + "\t");
			}
			abw.write("\n");
			for (int i = 0; i < para_num; i++) {
				for (int j = 0; j < init_num + 1; j++) {
					abw.write(result_a[i][j] + "\t");
				}
				abw.write("\n");
			}
			abw.close();

			File cfile = new File("result_c"+time+".txt");
			cfile.createNewFile();
			FileWriter cfilewriter = new FileWriter(cfile, true);
			BufferedWriter cbw = new BufferedWriter(cfilewriter);
			cbw.write("para\t");
			for (int i = 0; i < init_num; i++) {
				cbw.write("ini" + i + "\t");
			}
			cbw.write("\n");
			for (int i = 0; i < para_num; i++) {
				for (int j = 0; j < init_num + 1; j++) {
					cbw.write(result_c[i][j] + "\t");
				}
				cbw.write("\n");
			}
			cbw.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);

		}
	}
}
