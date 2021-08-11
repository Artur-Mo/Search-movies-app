// Graphical interface of the app, using Swing library

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GUI extends JFrame {
	private static int page = 1;
	private static String m_title="";
	private static ArrayList<String> titles = new ArrayList<String>();
	private static final String POSTER_URL = "https://image.tmdb.org/t/p/w500/";

	private JButton btnSearch = new JButton("Search Movie");
	private JButton btnNext = new JButton("Next page");
	private JButton btnPrev = new JButton("Previous page");

	private JPanel pnlData = new JPanel();
	private JPanel pnlSearch = new JPanel();

	public GUI() {
		super("MoviesApp");
		setLayout(new BorderLayout());
		setVisible(true);
		setSize(690, 420);

		pnlSearch.setLayout(new GridLayout(5, 5, 5, 5));
		pnlData.setLayout(new GridLayout(1, 1, 5, 5));

		pnlSearch.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.green), "Functions"));
		pnlData.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.blue), "Movies"));

		pnlSearch.add(btnSearch);
		pnlSearch.add(btnNext);
		pnlSearch.add(btnPrev);

		add(pnlSearch, BorderLayout.EAST);
		add(pnlData, BorderLayout.CENTER);

		//action listening for functional buttons
		ButtonsListener listener = new ButtonsListener();
		btnSearch.addActionListener(listener);
		btnNext.addActionListener(listener);
		btnPrev.addActionListener(listener);
	}

	private class ButtonsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == btnSearch) {
				m_title = JOptionPane.showInputDialog(null,"Enter movie name:");
				try {
					showList(m_title,page=1);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (event.getSource() == btnNext) {
				try {
					showList(m_title,page = page+1);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (event.getSource() == btnPrev) {
				if(page>1)
				try {
					showList(m_title,page = page-1);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void showList(String m_name, int page) throws IOException, InterruptedException {
			titles.clear();

			AppLogics search = new AppLogics(m_name,page);
			for(int i=0;i<search.list.results.length;i++) {
					titles.add(search.list.results[i].title);
			}

			pnlData.removeAll();
			JList jlist = new JList(titles.toArray());
			pnlData.add(jlist);
			setVisible(true);

			jlist.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent click) {
					if (click.getClickCount() == 1) {
						JList target = (JList)click.getSource();
						int index = target.locationToIndex(click.getPoint());
						if (index >= 0) {
							Object item = target.getModel().getElementAt(index);
							ImageIcon poster = null;
							try {
								poster = new ImageIcon(new URL(POSTER_URL+search.list.results[index].poster_path));
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}
							JOptionPane.showMessageDialog(null,"Title: "+ search.list.results[index].title+"\n"+
									"Release date: "+ search.list.results[index].release_date+ "\n"+
									"Rating: "+ search.list.results[index].vote_average+ "\n"+
									"Overview: "+search.list.results[index].overview.replaceAll("\\. ", ".\n"),"Info",JOptionPane.INFORMATION_MESSAGE,poster);
						}
					}
				}
			});
		}
	}
}