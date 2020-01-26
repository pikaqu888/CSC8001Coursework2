import java.util.ArrayList;

/**
 * A class which extends from ArrayList class and add the value in ascending order.
 * @param <E> generic parameter
 */
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E>
{
    /**
     *
     * @param value a generic type variable
     */
    public void insertSorted(E value)
    {
        int index = 0;
        for (int i = 0; i <= this.size()-1; i++)
        {
            if (value.compareTo(this.get(i)) < 0)
            {
                index = i;
                break;
            }
            index++;
        }
        this.add(index, value);
    }

}
