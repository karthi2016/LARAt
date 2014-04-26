package melodi.srcTest;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;

import melodi.internal.Item;
import melodi.internal.SE;
import melodi.main.MIG_JFrame;
import melodi.main.MIG_JPanel_Parent;

import org.junit.Assert;
import org.junit.Test;

public class TestIO {

	MIG_JPanel_Parent mig_parent;
	boolean visible = true;
	int test = 100;
	int test2 = 100;
	int fin = 10;

	public TestIO() {

		// Turn off metal's use of bold fonts

		if (visible) {

			UIManager.put("swing.boldMetal", Boolean.FALSE);
			MIG_JFrame annotationSE_jframe = new MIG_JFrame("MIG Interface");
			this.mig_parent = new MIG_JPanel_Parent(annotationSE_jframe);

			mig_parent.addMenu();
			// ImageIcon leftButtonIcon = new ImageIcon("images/star.png");
			// annotationSE_jframe.setIconImage(leftButtonIcon.getImage());
			annotationSE_jframe
					.setDefaultCloseOperation(MIG_JFrame.EXIT_ON_CLOSE);
			annotationSE_jframe.setSize(1024, 768); // On donne une taille à
			annotationSE_jframe.setLocationRelativeTo(null); // On centre la
																// fenêtre sur
																// l'écran
			annotationSE_jframe.setResizable(true); // On interdit la
													// redimensionnement de la
													// fenêtre

			// Add content to the window.

			annotationSE_jframe.add(mig_parent);

			// Display the window.
			// annotationSE_jframe.pack();
			annotationSE_jframe.setVisible(true);

			mig_parent.document_path = "resources/test/Test_corpus/Abattoir.html";

			mig_parent.removeXML();

		} else {

			this.mig_parent = new MIG_JPanel_Parent(null);
			mig_parent.document_path = "resources/test/Test_corpus/Abattoir.html";

			mig_parent.removeXML();
		}

	}

	private void wait(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Test
	public void testSuite() {
		// ouver un document
		openADocument();
		if (visible)
			wait(test);

		// Ajoute une SE de 10 à 50
		// Vérifie que la Chaîne compte bien 1 élément
		addSE(4440, 4662, 1);
		if (visible)
			wait(test);
		addSE(2606, 2984, 2);
		if (visible)
			wait(test);
		testSort(2606);

		// SE, itemID, begin, end
		addPrimer(0, 1, 26);
		if (visible)
			wait(test);
		addItem(0, 0, 245, 287);
		if (visible)
			wait(test);
		addItem(0, 1, 27, 200);
		if (visible)
			wait(test);
		addItem(0, 2, 288, 334);
		if (visible)
			wait(test);
		addItem(0, 3, 335, 379);
		if (visible)
			wait(test);
		addItem(0, 4, 201, 244);

		// Vérifie l'ordre des items
		// 0 car au chargement c'est une nouveau doc vide.
		recTest(0, 0, 27, 200);
		recTest(0, 2, 245, 287);

		// Deuxième SE
		if (visible)
			wait(test);
		addPrimer(1, 1, 23);
		if (visible)
			wait(test);
		addItem(1, 0, 90, 150);
		if (visible)
			wait(test);
		addItem(1, 1, 24, 89);
		if (visible)
			wait(test);
		addItem(1, 2, 151, 223);
		if (visible)
			wait(test);
		recTest(1, 1, 90, 150);

		if (visible)
			runAndwait();
		// SE 3
		// addItem(2, 0, 24, 89);
		// recTest(2, 0, 24, 89);

	}

	public void runAndwait() {

		// Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		MIG_JFrame annotationSE_jframe = new MIG_JFrame("MIG Interface");
		MIG_JPanel_Parent mig = new MIG_JPanel_Parent(annotationSE_jframe);
		mig.addMenu();
		// ImageIcon leftButtonIcon = new ImageIcon("images/star.png");
		// annotationSE_jframe.setIconImage(leftButtonIcon.getImage());

		annotationSE_jframe.setDefaultCloseOperation(MIG_JFrame.EXIT_ON_CLOSE);
		annotationSE_jframe.setSize(1024, 768); // On donne une taille à notre
												// fenêtre
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x
				- (annotationSE_jframe.getWidth() / 2), middle.y
				- (annotationSE_jframe.getHeight() / 2));
		annotationSE_jframe.setLocation(newLocation); // On centre la fenêtre
														// sur l'écran
		annotationSE_jframe.setResizable(true); // On interdit la
												// redimensionnement de la
												// fenêtre

		// Add content to the window.

		annotationSE_jframe.add(mig);

		// Display the window.
		// annotationSE_jframe.pack();
		annotationSE_jframe.setVisible(true);
		mig.document_path = "ressources/test/corpus/Abattoir.html";
		mig.initDoc();
		wait(500);

		for (SE mySE : mig.chain_SE) {
			mig.current_SE = mySE;
			mig.init();

			if (visible) {
				wait(test2);
			}
		}

		if (visible)
			wait(fin);

	}

	// private void afficheDocument(){
	//
	// HashMap<SE, HTMLDocument> hash_doc = mig_parent.hashSE_Doc;
	//
	// for (SE seKey : hash_doc.keySet()) {
	// try {
	// System.out.println(hash_doc.get(seKey).getText(0,
	// hash_doc.get(seKey).getLength()));
	// } catch (BadLocationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// System.out.println("Fin");
	//
	// }

	private void openADocument() {
		int length_of_document = mig_parent.initDoc();
		Assert.assertEquals(23637, length_of_document);
		String name = mig_parent.document_name;
		Assert.assertEquals("Abattoir.html", name);
	}

	private void addSE(int a, int b, int sizeExpected) {
		mig_parent.deb_current_selection_textPane = a;
		mig_parent.fin_current_selection_textPane = b;
		try {
			mig_parent.current_selection_textPane = mig_parent.textPane
					.getText(a, b - a);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mig_parent.actionPerformedAddSE();
		if(visible){
			wait(test);
		}
		mig_parent.actionPerformedRecSE();

		int deb = mig_parent.current_SE.getIndice_begin();
		int fin = mig_parent.current_SE.getIndice_end();

		int size = mig_parent.chain_SE.size();

		Assert.assertEquals(a, deb);
		Assert.assertEquals(b, fin);
		Assert.assertEquals(sizeExpected, size);
	}

	private void testSort(int firstBeginExpected) {

		LinkedList<SE> toTest = mig_parent.chain_SE;
		SE mySE = toTest.get(0);

		Assert.assertEquals(firstBeginExpected, mySE.getIndice_begin());

	}

	private void addPrimer(int indexSE, int deb, int fin) {
		mig_parent.current_SE = mig_parent.chain_SE.get(indexSE);
		mig_parent.init();
		// System.out.println("current_SE deb : "+
		// mig_parent.current_SE.getIndice_begin());
		// System.out.println("current_SE end : "+
		// mig_parent.current_SE.getIndice_end());
		mig_parent.deb_current_selection_SEPane = deb;
		mig_parent.fin_current_selection_SEPane = fin;

		mig_parent.actionPerformedAddPrimer();

		Assert.assertEquals(deb, mig_parent.current_SE.getPrimer()
				.getIndice_begin());
		Assert.assertEquals(fin, mig_parent.current_SE.getPrimer()
				.getIndice_end());

		mig_parent.init();
	}

	private void addItem(int indexSE, int indexItem, int deb, int fin) {

		mig_parent.current_SE = mig_parent.chain_SE.get(indexSE);
		mig_parent.init();
		System.out.println("current_SE deb : "
				+ mig_parent.current_SE.getIndice_begin());
		System.out.println("current_SE end : "
				+ mig_parent.current_SE.getIndice_end());
		mig_parent.deb_current_selection_SEPane = deb;
		mig_parent.fin_current_selection_SEPane = fin;

		mig_parent.actionPerformedAddItem();

		Assert.assertEquals(deb, mig_parent.current_SE.getItem(indexItem)
				.getIndice_begin());
		Assert.assertEquals(fin, mig_parent.current_SE.getItem(indexItem)
				.getIndice_end());

		mig_parent.init();
	}

	private void recTest(int indexSE, int indexItem, int deb, int fin) {

		String document_pathExpected = mig_parent.document_path;
		mig_parent.actionPerformedValid();

		MIG_JPanel_Parent newMIG = new MIG_JPanel_Parent(null);
		newMIG.document_path = document_pathExpected;
		// newMIG.initDoc();

		Assert.assertEquals(23637, newMIG.initDoc());

		String name = newMIG.document_name;
		Assert.assertEquals("Abattoir.html", name);

		LinkedList<SE> chain_newMIG_SE = newMIG.chain_SE;
		SE toTest = chain_newMIG_SE.get(indexSE);

		Assert.assertEquals(deb, toTest.getItem(indexItem).getIndice_begin());
		Assert.assertEquals(fin, toTest.getItem(indexItem).getIndice_end());
	}

}