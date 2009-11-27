package game;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 * @author Xiagu
 */
public class Animation implements Collection<BufferedImage>,
		Iterator<BufferedImage> {

	/**
	 * The list of all frames this <code>Animation</code> possesses.
	 */
	private LinkedList<BufferedImage> frames;

	/**
	 * The current frame.
	 */
	private int frame;

	/**
	 * Contains whether or not <code>next</code> has been called before
	 * <code>remove</code>.
	 */
	private boolean nextCalled;

	/**
	 * Contains whether or not this animation loops forever.
	 */
	private boolean loops;

	/**
	 * Constructs a new Animation.
	 * 
	 * @param loops
	 *            whether this Animation loops or not.
	 */
	public Animation(boolean loops) {
		frames = new LinkedList<BufferedImage>();
		frame = 0;
		this.loops = loops;
	}

	public Animation(BufferedImage[] imgs, boolean loops) {
		this(loops);
		for (BufferedImage i : imgs)
			frames.add(i);
	}

	public Animation(Collection<BufferedImage> imgs, boolean loops) {
		this(loops);
		frames.addAll(imgs);
	}

	/**
	 * @returns the frame number this Animation is on.
	 */
	public int getFrame() {
		return frame;
	}

	public boolean add(BufferedImage i) {
		if (i == null)
			throw new NullPointerException();
		frames.add(i);
		return true;
	}

	public boolean addAll(Collection<? extends BufferedImage> c) {
		for (BufferedImage img : c) {
			if (img == null)
				throw new NullPointerException();
			frames.add(img);
		}
		return true;
	}

	public void clear() {
		frames.clear();
	}

	public boolean contains(Object o) {
		return frames.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return frames.containsAll(c);
	}

	public boolean isEmpty() {
		return frames.isEmpty();
	}

	public Iterator<BufferedImage> iterator() {
		throw new UnsupportedOperationException(
				"Use this object for it, dumbass");
	}

	public boolean remove(Object o) {
		return frames.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return frames.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return frames.retainAll(c);
	}

	public int size() {
		return frames.size();
	}

	public Object[] toArray() {
		return frames.toArray();
	}

	public <T> T[] toArray(T[] arr) {
		return frames.toArray(arr);
	}

	public boolean hasNext() {
		return frame + 1 < frames.size();
	}

	public BufferedImage next() {
		frame++;
		if (frame >= frames.size())
			if (loops)
				frame = 0;
			else
				throw new NoSuchElementException("index:" + frame);
		nextCalled = true;
		return frames.get(frame);
	}

	public void remove() {
		if (nextCalled) {
			frames.remove(frame);
			nextCalled = false;
		} else
			throw new IllegalStateException();
	}

}
