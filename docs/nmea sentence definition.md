Tells if the specified String matches the NMEA 0183 sentence format.

String is considered as a sentence if it meets the following criteria:

 * First character is '$' or '!'
 * Begin char is followed by upper-case sentence ID (3 to 10 chars)
 * Sentence ID is followed by a comma and an arbitrary number of printable ASCII characters (payload data)
 * Data is followed by '*' and a two-char hex checksum (may be omitted)

Notice that format matching is not strict; although NMEA 0183 defines a maximum length of 80 chars, the sentence length is not checked. This is due to fact that it seems quite common that devices violate this rule, some perhaps deliberately, some by mistake. Thus, assuming the formatting is otherwise valid, it is not feasible to strictly validate length and discard sentences that just exceed the 80 chars limit.



