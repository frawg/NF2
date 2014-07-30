package protocol.IPv4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {
	private short first, second, third, fourth;
	
	public Address(int first, int second, int third, int fourth)
	{
		this.first = (short)getAvalid(first);
		this.second = (short)getAvalid(second);
		this.third = (short)getAvalid(third);
		this.fourth = (short)getAvalid(fourth);
	}
	
	public Address(int i)
	{
		int[] m = toArrayFromInt(i);
		first = (short) m[0];
		second = (short) m[1];
		third = (short) m[2];
		fourth = (short) m[3];
	}
	
	public static boolean isValid(int first, int second, int third, int fourth)
	{ return (check(first) || check(second) || check(third) || check(fourth)); }
	
	private static boolean check(int i)
	{ return (i < 256 && i >= 0); }
	
	private static int getAvalid(int i)
	{ return Math.min(i, 255); }
	
	private int[] toOctetArray()
	{ return new int[]{first, second, third, fourth}; }

	public String toString()
	{ return (first + "." + second + "." + third + "." + fourth); }
	
	public int toInt() {
        int addr = 0;
        addr |= ((first & 0xff) << 8*(4-1));
        addr |= ((second & 0xff) << 8*(4-2));
        addr |= ((third & 0xff) << 8*(4-3));
        addr |= ((fourth & 0xff) << 8*(4-4));
        return addr;
    }
	
	private int countBits(int x)
	{
		x = x - ((x >>> 1) & 0x55555555);
		x = (x & 0x33333333) + ((x >>> 2) & 0x33333333);
		x = (x + (x >>> 4)) & 0x0F0F0F0F;
		x = x + (x >>> 8);
		x = x + (x >>> 16);
		return x & 0x0000003F;
	}

	private static boolean rangeCheck(int value, int begin, int end) { return (value > begin && value <= end); }

	public static int CIDRtoInt(int i) { // for example, key in /27
		int netmask = 0;
		if(i < 32 || i > 0)
		{
		/* Create a binary netmask from the number of bits specification /x */
			int cidrPart = 0;
			if (rangeCheck(i, 0, 32))
				cidrPart = i;
			else
				return netmask;
			for (int j = 0; j < cidrPart; ++j) {
				netmask |= (1 << 31-j);
			}
		}
		return netmask;
	}

	public static String IntToString(int i)
	{
		return format(toArrayFromInt(i));
	}
	
	// from octets to string
    private static String format(int[] octets) {
        StringBuilder str = new StringBuilder();
        for (int i =0; i < octets.length; ++i){
            str.append(octets[i]);
            if (i != octets.length - 1) {
                str.append(".");
            }
        }
        return str.toString();
    }
	
	// toArrayFromInt(packAsIn(24)) will give x.x.x.x
	private static int[] toArrayFromInt(int val) {
        int ret[] = new int[4];
        for (int j = 3; j >= 0; --j) {
            ret[j] |= ((val >>> 8*(3-j)) & (0xff));
        }
        return ret;
    }
}